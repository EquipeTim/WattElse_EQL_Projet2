package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.TransactionDao;
import fr.eql.ai116.proj2.tim.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.proj2.tim.entity.Payment;
import fr.eql.ai116.proj2.tim.entity.PaymentRefusalType;
import fr.eql.ai116.proj2.tim.entity.PricingType;
import fr.eql.ai116.proj2.tim.entity.Reservation;
import fr.eql.ai116.proj2.tim.entity.Transaction;
import fr.eql.ai116.proj2.tim.entity.dto.PaymentDto;
import fr.eql.ai116.proj2.tim.entity.dto.ReservationDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
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
    private static final String REQ_GET_USER_TRANSACTIONS = "SELECT * FROM transaction " +
            "WHERE id_user = ? AND reservation_date > ?";
    private static final String REQ_GET_TRANSACTION_DETAILS =
            "SELECT t.id_transaction,t.id_payment,t.id_user as id_client, " +
                    "t.start_date_charging,t.end_date_charging,t.consume_quantity, " +
                    "t.monetary_amount, t.reservation_date, cs.*, pr.*, p.*, pt.* " +
                    "FROM transaction t " +
                    "JOIN charging_station cs ON t.id_charging_station = cs.id_charging_station " +
                    "JOIN pricing pr ON pr.id_charging_station  = cs.id_charging_station " +
                    "JOIN pricing_type pt ON pt.id_type_pricing = pr.id_type_pricing " +
                    "JOIN payment p ON p.id_payment = t.id_payment " +
                    "WHERE id_transaction = ?";

    private static final String REQ_FILL_CONSUMPTION = "UPDATE transaction SET consume_quantity = ? " +
            "WHERE id_transaction = ?";
    private static final String REQ_CALCULATE_TOTAL = "UPDATE transaction t " +
            "JOIN pricing p ON t.id_charging_station = p.id_charging_station " +
            "SET t.monetary_amount = p.price * t.consume_quantity WHERE t.id_transaction = ?";

    private static final String REQ_UPDATE_RESERVATION_PAYMENT_DATE =
            "UPDATE transaction SET date_payment = ? WHERE id_transaction = ?";
    private static final String REQ_EXECUTE_PAYMENT = "UPDATE payment SET id_credit_card = ?, id_bank_account = ? ," +
            "payment_date = ?, payment_amount = ? WHERE id_payment = ?";
    private static final String REQ_REFUSE_PAYMENT =
            "UPDATE transaction SET id_payment_refuse_type = ? WHERE id_transaction = ?";
    /**
     * Reserve a charging station
     * @param reservationDto
     */
    @Override
    public Reservation reserveStation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try {
                Calendar utc = Calendar.getInstance(TimeZone.getTimeZone(reservationDto.getTimeZone()));
                LocalDateTime now = LocalDateTime.now(ZoneId.of(reservationDto.getTimeZone()));
                Long paymentId = addNewPayment(reservationDto, connection);
                PreparedStatement statement = connection.prepareStatement(REQ_RESERVE_STATION,  Statement.RETURN_GENERATED_KEYS);
                statement.setLong(1, reservationDto.getIdUser());
                statement.setLong(2, paymentId);
                statement.setLong(3, reservationDto.getIdStation());
                statement.setTimestamp(4, Timestamp.valueOf(now), utc);//Timestamp.from(now.toInstant()));
                statement.setTimestamp(5, Timestamp.valueOf(reservationDto.getReservationDate()));
                statement.setInt(6, reservationDto.getReservationDuration());
                int affectedRows = statement.executeUpdate();
                connection.commit();
                if (affectedRows > 0) {
                    ResultSet resultSet = statement.getGeneratedKeys();
                    if (resultSet.next()) {
                        reservation.setReservationId(resultSet.getLong(1));
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
     * Time formatter helper function
     * @param time
     * @return
     */
    private String reformatTimestamp(Timestamp time){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormatter.setTimeZone(TimeZone.getDefault());
        return dateFormatter.format(time);
    }

    /**
     * Marks reservation as started charging
     * @param reservationId
     */
    @Override
    public Transaction startCharging(Long reservationId) {
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
                        return new Transaction(1L, "Heure de début de charge enregistrée: " + reformatTimestamp(now), reservationId);
                    } catch (SQLException e) {
                        logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
                    }
                } else {
                    return new Transaction(2L, "La durée de réservation épuisé", reservationId);
                }
            }
            return new Transaction(3L, "Récharge déjà commencé", reservationId);
            }
        return new Transaction(0L, "Réservation non trouvé", reservationId);
    }



    /**
     * Indicate the end of charging
     * @param reservationId
     * @return
     */
    @Override
    public Transaction stopCharging(Long reservationId) {
        Timestamp now = Timestamp.from(Instant.now());
        Reservation reservation = getReservation(reservationId);
        if (reservation != null) {
            if (reservation.getRechargeStartTime() == null) {
                return new Transaction(2L, "Recharge pas encoré commencé", reservationId);
            }
            if (reservation.getRechargeEndTime() == null) {
                try (Connection connection = dataSource.getConnection()) {
                    PreparedStatement statement = connection.prepareStatement(REQ_END_CHARGE);
                    statement.setTimestamp(1, now);
                    statement.setLong(2, reservationId);
                    statement.executeUpdate();
                    fillConsumtionAndCalculatePrice(reservationId);

                    Transaction transaction = generateTransactionInfo(reservationId);
                    transaction.setStatusId(1L);
                    transaction.setStatus("Recharge terminé");
                    return transaction;
                } catch (SQLException e) {
                    logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
                }
            } else {
                return new Transaction(3L, "La récharge de cette réservation déjà terminé", reservationId);
            }
        }
        return new Transaction(0L, "Réservation non trouvé", reservationId);
    }

    private void fillConsumtionAndCalculatePrice(Long reservationId){
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try {
                fillConsumption(reservationId, connection);
                calculatePrice(reservationId, connection);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error("Une erreur s'est produite lors de Calculation de prix pour transaction: " + reservationId, e);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
    }

    private void fillConsumption(Long reservationId, Connection connection) throws SQLException {
        float quantity = new Random().nextFloat() * 100;
        PreparedStatement statement = connection.prepareStatement(REQ_FILL_CONSUMPTION);
        statement.setFloat(1, quantity);
        statement.setLong(2, reservationId);
        statement.executeUpdate();
    }

    private void calculatePrice(Long reservationId, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(REQ_CALCULATE_TOTAL);
        statement.setLong(1, reservationId);
        statement.executeUpdate();
    }

    @Override
    public Transaction generateTransactionInfo(Long reservationId) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_GET_TRANSACTION_DETAILS);
            statement.setLong(1, reservationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Timestamp startTime = resultSet.getTimestamp("start_date_charging");
                Timestamp endTime = resultSet.getTimestamp("end_date_charging");
                LocalDateTime startDate = startTime != null ? startTime.toLocalDateTime() : null;
                LocalDateTime endDate = endTime != null ? endTime.toLocalDateTime() : null;
                Transaction transaction = new Transaction(
                        resultSet.getLong("id_payment"),
                        resultSet.getLong("id_transaction"),
                        resultSet.getLong("id_client"),
                        resultSet.getLong("id_user"),
                        resultSet.getTimestamp("reservation_date").toLocalDateTime(),
                        startDate,
                        endDate,
                        resultSet.getFloat("consume_quantity"),
                        PricingType.valueOf(resultSet.getString("type_pricing")).getLabel(),
                        resultSet.getFloat("price"));
                return transaction;

            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
        return null;
    }

    @Override
    public List<Transaction> getUserTransactions(Long userId, String date) {
        List<Transaction> transactions = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_GET_USER_TRANSACTIONS);
            statement.setLong(1, userId);
            statement.setString(2, date);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                transactions.add(generateTransactionInfo(resultSet.getLong("id_transaction")));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
        return transactions;
    }

    /**
     * Does the payment for the reservation
     * @param paymentDto
     * @return
     */
    @Override
    public Payment pay(PaymentDto paymentDto) {
        Payment payment = null;
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try {
                Timestamp now = Timestamp.from(Instant.now());
                executePayment(paymentDto.getIdReservation(), paymentDto.getIdAccountForPayment(),
                        paymentDto.getIdCardForPayment(), now, connection);
                updateTransaction(paymentDto.getIdReservation(), now, connection);
                processPayment(paymentDto.getIdReservation(), connection);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error("Une erreur s'est produite lors de paiement pour la transaction: "
                        + paymentDto.getIdReservation(), e);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
        return payment;
    }

    /**
     * Simulates payment processing x% chance to refuse a payment
     * @param reservationId
     * @param connection
     * @throws SQLException
     */
    private void processPayment(Long reservationId, Connection connection) throws SQLException{
        if (new Random().nextDouble() < 0.1) {
            Random random = new Random();
            int reasonId =  random.nextInt(PaymentRefusalType.values().length)+1;
            PreparedStatement statement = connection.prepareStatement(REQ_REFUSE_PAYMENT);
            statement.setLong(1, reasonId);
            statement.setLong(2, reservationId);
            statement.executeUpdate();
        }
    }

    /**
     *      * Updates the transaction table and ,arks reservation as paid
     * @param reservationId
     * @param now timestamp now
     * @param connection
     * @throws SQLException
     */
    private void updateTransaction(Long reservationId, Timestamp now,
                                   Connection connection) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(REQ_UPDATE_RESERVATION_PAYMENT_DATE);
        statement.setTimestamp(1, now);
        statement.setLong(2, reservationId);
        statement.executeUpdate();
    }

    /**
     * Executes the payment (changes the payment table)
     * @param idReservation
     * @param idAccount
     * @param idCard
     * @param now
     * @param connection
     * @throws SQLException
     */
    private void executePayment(Long idReservation, Long idAccount, Long idCard, Timestamp now, Connection connection)
            throws SQLException{
        Transaction transaction = generateTransactionInfo(idReservation);
        PreparedStatement statement = connection.prepareStatement(REQ_EXECUTE_PAYMENT);
        if (idCard != null) {
            statement.setLong(1, idCard);
        } else {
            statement.setNull(1, Types.INTEGER);
        }
        if (idAccount != null) {
            statement.setLong(2, idAccount);
        } else {
            statement.setNull(2, Types.INTEGER);
        }
        statement.setTimestamp(3, now);
        statement.setFloat(4,transaction.getMonetaryAmount());
        statement.setLong(5, transaction.getIdPayment());
        statement.executeUpdate();
    }


    /**
     * Gets reservation by the ID
     * @param reservationId
     * @return
     */
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
                        resultSet.getTimestamp("start_date_charging"),
                        resultSet.getTimestamp("end_date_charging"));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
        return reservation;
    }
    /**
     * Creates new payment entry in the DB related to specific transaction
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
     * Adds card of account payment related to specific transaction
     * @param REQ request
     * @param idMethod card or account ID from DB used for payment
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
