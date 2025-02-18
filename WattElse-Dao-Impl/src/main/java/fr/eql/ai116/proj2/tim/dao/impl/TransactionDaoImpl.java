package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.CarDao;
import fr.eql.ai116.proj2.tim.dao.TransactionDao;
import fr.eql.ai116.proj2.tim.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.proj2.tim.entity.AccountCloseType;
import fr.eql.ai116.proj2.tim.entity.dto.ReservationDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.time.Instant;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;


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

    /**
     * Reserve a charging station
     * @param reservationDto
     */
    @Override
    public void reserveStation(ReservationDto reservationDto) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try {
                Long paymentId = addNewPayment(reservationDto, connection);
                PreparedStatement statement = connection.prepareStatement(REQ_RESERVE_STATION);
                statement.setLong(1, reservationDto.getIdUser());
                statement.setLong(2, paymentId);
                statement.setLong(3, reservationDto.getIdStation());
                statement.setTimestamp(4, Timestamp.from(Instant.now()));
                statement.setDate(5, Date.valueOf(reservationDto.getReservationDate()));
                statement.setInt(6, reservationDto.getReservationDuration());
                statement.executeUpdate();
                connection.commit();
                logger.info("Borne id {} a été reservé par utilisateur id {}", reservationDto.getIdStation(), reservationDto.getIdUser());
            } catch (SQLException e) {
                connection.rollback();
                logger.error("Une erreur s'est produite lors de réservation", e);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
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
