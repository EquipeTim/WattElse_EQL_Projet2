package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.CarDao;
import fr.eql.ai116.proj2.tim.dao.TransactionDao;
import fr.eql.ai116.proj2.tim.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.proj2.tim.entity.AccountCloseType;
import fr.eql.ai116.proj2.tim.entity.Reservation;
import fr.eql.ai116.proj2.tim.entity.dto.ChoicesDto;
import fr.eql.ai116.proj2.tim.entity.dto.ReservationDto;
import fr.eql.ai116.proj2.tim.entity.dto.TransactionDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;


@Remote(TransactionDao.class)
@Stateless
public class TransactionDaoImpl implements TransactionDao {
    private static final Logger logger = LogManager.getLogger();
    private final DataSource dataSource = new WattElseDataSource();

    private static final String REQ_RESERVE_STATION = "INSERT INTO transaction (id_user, id_payment, " +
            "id_charging_station, registration_reservation_date, reservation_date, reservation_duration)" +
            " VALUES (?,?,?,?,?,?)";
    private static final String REQ_ADD_CARD_PAYMENT = "INSERT INTO payment (id_credit_card) VALUES (?)";
    private static final String REQ_ADD_ACCOUNT_PAYMENT = "INSERT INTO payment (id_bank_account) VALUES (?)";
    private static final String REQ_START_CHARGE = "UPDATE transaction SET start_date_charging = ? " +
            "WHERE id_transaction = ?";
    private static final String REQ_END_CHARGE = "UPDATE transaction SET end_date_charging = ? " +
            "WHERE id_transaction = ?";
    private static final String REQ_GET_RESERVATION = "SELECT * FROM transaction WHERE id_transaction = ? ";
    private static final String REQ_GET_TRANSACTION_DETAILS = "SELECT * FROM transaction t " +
            "JOIN charging_station cs ON t.id_charging_station = cs.id_charging_station " +
            "JOIN pricing p ON p.id_charging_station " +
            "JOIN pricing_type pt ON pt.id_type_pricing " +
            "JOIN WHERE id_transaction = ?";
    /**
     * Reserve a charging station
     * @param reservationDto
     */
    @Override
    public ReservationDto reserveStation(ReservationDto reservationDto) {
        ReservationDto reservation = new ReservationDto();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try {
                Long paymentId = addNewPayment(reservationDto, connection);
                PreparedStatement statement = connection.prepareStatement(REQ_RESERVE_STATION, Statement.RETURN_GENERATED_KEYS);
                statement.setLong(1, reservationDto.getIdUser());
                statement.setLong(2, paymentId);
                statement.setLong(3, reservationDto.getIdStation());
                statement.setTimestamp(4, Timestamp.from(Instant.now()));
                statement.setDate(5, Date.valueOf(reservationDto.getReservationDate()));
                statement.setInt(6, reservationDto.getReservationDuration());
                int affectedRows = statement.executeUpdate();
                connection.commit();
                if (affectedRows > 0) {
                    ResultSet resultSet = statement.getGeneratedKeys();
                    if (resultSet.next()) {
                        reservation.setIdReservation(resultSet.getLong(1));
                        reservation.setReservationDuration(reservationDto.getReservationDuration());
                    }
                }
                logger.info("Borne id {} a été reservé par utilisateur id {}", reservationDto.getIdStation(), reservationDto.getIdUser());
            } catch (SQLException e) {
                connection.rollback();
                logger.error("Une erreur s'est produite lors de réservation", e);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
        return reservation;
    }

    /**
     * Marks reservation as started charging
     * @param reservationId
     */
    @Override
    public ChoicesDto startCharging(Long reservationId) {
        Timestamp now = Timestamp.from(Instant.now());
        Reservation reservation = getReservation(reservationId);
        if (reservation != null){
            if (reservation.getRechargeStartTime() == null) {
                if (reservation.reservationValid(now)) {
                    try (Connection connection = dataSource.getConnection()) {
                        PreparedStatement statement = connection.prepareStatement(REQ_START_CHARGE);
                        statement.setTimestamp(1, now);
                        statement.setLong(2, reservationId);
                        statement.executeUpdate();
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        dateFormatter.setTimeZone(TimeZone.getDefault());
                        return new ChoicesDto(1L, "Heure de début de charge enregistrée: " + dateFormatter.format(now));
                    } catch (SQLException e) {
                        logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
                    }
                } else {
                    return new ChoicesDto(2L, "La durée de réservation épuisé");
                }
            }
            return new ChoicesDto(3L, "Récharge déjà commencé");
        }
        return new ChoicesDto(0L, "Réservation non trouvé ou la recharge déjà commencé");
    }

    @Override
    public void stopCharging(Long reservationId) {
        Timestamp now = Timestamp.from(Instant.now());
        Reservation reservation = getReservation(reservationId);
        if (reservation != null){
            try (Connection connection = dataSource.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(REQ_END_CHARGE);
                statement.setTimestamp(1, now);
                statement.setLong(2, reservationId);
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
            }
        }
    }

    @Override
    public TransactionDto generatePayment(Long reservationId) {
        TransactionDto transactionDto = null;
//        try (Connection connection = dataSource.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(REQ_GET_R);
//            statement.setLong(1, reservationId);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                transactionDto = new TransactionDto(resultSet.getLong("id_user"),
//                        resultSet.getLong("id_transaction"),
//                        resultSet.getTimestamp("reservation_date"),
//                        resultSet.getInt("reservation_duration"));
//            }
//        } catch (SQLException e) {
//            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
//        }
        return transactionDto;
    }


    private Reservation getReservation(Long reservationId){
        Reservation reservation = null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_GET_RESERVATION);
            statement.setLong(1, reservationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                reservation = new Reservation(resultSet.getLong("id_user"),
                        resultSet.getLong("id_transaction"),
                        resultSet.getTimestamp("reservation_date"),
                        resultSet.getInt("reservation_duration"),
                        resultSet.getTimestamp("start_date_charging"));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
        return reservation;
    }
    /**
     * Creates new payment entry in the DB
     * @param reservationDto
     * @param connection
     * @return
     * @throws SQLException
     */
    private Long addNewPayment(ReservationDto reservationDto, Connection connection) throws SQLException {
        Long paymentId;
        if (reservationDto.getIdUserBankCard() != null){
            paymentId = addPayment(REQ_ADD_CARD_PAYMENT, reservationDto.getIdUserBankCard(), connection);
        } else {
            paymentId = addPayment(REQ_ADD_ACCOUNT_PAYMENT, reservationDto.getIdUserBankAccount(), connection);
        }
        return paymentId;
    }

    /**
     * Adds card of account payment
     * @param REQ request
     * @param idMethod card or account ID fro, DB used for payment
     * @param connection
     * @return
     * @throws SQLException
     */
    private Long addPayment(String REQ, Long idMethod, Connection connection) throws SQLException {
        Long paymentId = null;
        PreparedStatement statement = connection.prepareStatement(REQ, Statement.RETURN_GENERATED_KEYS);
        statement.setLong(1, idMethod);
        int affectedRows = statement.executeUpdate();
        if (affectedRows > 0) {
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                paymentId = resultSet.getLong(1);
            }
        }
        return paymentId;
    }
}
