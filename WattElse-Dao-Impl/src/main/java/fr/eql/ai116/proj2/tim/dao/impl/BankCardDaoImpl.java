package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.BankCardDao;
import fr.eql.ai116.proj2.tim.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.proj2.tim.entity.BankCard;
import fr.eql.ai116.proj2.tim.entity.Role;
import fr.eql.ai116.proj2.tim.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Remote(BankCardDao.class)
@Stateless
public class BankCardDaoImpl implements BankCardDao {
    private final DataSource dataSource = new WattElseDataSource();
    private static final Logger logger = LogManager.getLogger();



    private static final String REQ_ADD_CARD = "INSERT INTO credit_card (id_user, number_card, cardholder_name," +
            "expiration_date, cvv_number, registration_date_carte) VALUES (?,?,?,?,?,?) ";

    private static final String REQ_GET_ALL_USER_CARDS= "SELECT cardholder_name, expiration_date, cvv_number, id_credit_card, " +
            "RIGHT(number_card, 4) AS number_card FROM credit_card cc JOIN session s ON cc.id_user = s.id_user WHERE s.token = ? " +
            "AND withdrawal_date_card IS NULL";
    private static final String REQ_CARD_EXISTS = "SELECT * FROM credit_card WHERE number_card = ? and id_user =? and withdrawal_date_card IS NULL";
    private static final String REQ_CLOSE_CARD = "UPDATE credit_card SET withdrawal_date_card =? WHERE id_credit_card = ?";
    private static final String REQ_FIND_CARD_BY_ID ="SELECT * FROM credit_card WHERE id_credit_card = ?";



    private void addBankCard(BankCard bankCard, Long userId, Connection connection) throws SQLException {
        connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(REQ_ADD_CARD);
            statement.setLong(1, userId);
            statement.setString(2, bankCard.getNumberCard());
            statement.setString(3, bankCard.getCardHolderName());
            statement.setDate(4, Date.valueOf(bankCard.getExpirationDate()));
            statement.setLong(5,bankCard.getCvvNumber());
            statement.setTimestamp(6, Timestamp.from(Instant.now()));
            statement.executeUpdate();
        connection.commit();

            logger.info("Carte bancaire ajouté dans la base de données");

    }
    /**
     * Checks if user exists in the databse according to his email
     * @param bankCard
     * @param connection
     * @return true if he exists in DB
     */
    private boolean checkCardExists(BankCard bankCard, Long userId, Connection connection) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(REQ_CARD_EXISTS);
        statement.setString(1, bankCard.getNumberCard());
        statement.setLong(2, userId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }

   /**
     * Registers user to database; Verifies if he does not exist, adds city if needed
    * @param bankCard
    * @return true if card was added to DB
    */
    @Override
    public boolean registerCard(BankCard bankCard, Long userId) {
        try (Connection connection = dataSource.getConnection()) {
            boolean allowToRegister = checkCardExists(bankCard, userId, connection);
            if (allowToRegister) {
                logger.info("carte avec numero {} existe déjà", bankCard.getNumberCard());
                return false;
            } else {
                connection.setAutoCommit(false);
                addBankCard(bankCard, userId, connection);
                connection.commit();
                return true;
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
        return false;
    }

    /**
     * Closes user card
     * @param bankCardId
     * @return true if he if user closed successfully
     */
    @Override
    public boolean closeBankCard(Long bankCardId) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_CLOSE_CARD);

                // Set the withdrawal date to the current date (using java.sql.Date)
            statement.setTimestamp(1, Timestamp.from(Instant.now())); // Current date
            statement.setLong(2, bankCardId); // Set the card ID to close
            logger.error(statement);
            int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    // Optionally, you could log or use the closeReasonId if necessary, like saving the reason to a log table
                    logger.info("Card with ID {} successfully closed", bankCardId);
                    return true; // Successfully closed the card
                } else {
                    logger.warn("No card found with ID {}", bankCardId);
                    return false; // No card found with the provided ID, or it couldn't be closed
                }
            } catch (SQLException e) {
                logger.error("An error occurred while closing the card with ID {}", bankCardId, e);
                return false; // An error occurred while closing the card
            }
        }



    @Override
    public void modifyBankCard(BankCard creditCard) {

    }

    @Override
    public List<BankCard> getBankCards(String token) {
        List<BankCard> cards = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_GET_ALL_USER_CARDS);
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                cards.add(new BankCard(resultSet.getString("number_card"),
                                       resultSet.getString("cardholder_name"),
                                       resultSet.getDate("expiration_date").toLocalDate(),
                                       resultSet.getInt("cvv_number"),
                                       resultSet.getLong("id_credit_card")));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
        return cards;
    }

    @Override
    public BankCard getBankCardById(Long bankCardId) {
            BankCard bankCard= null;
            try (Connection connection = dataSource.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(REQ_FIND_CARD_BY_ID);
                statement.setLong(1, bankCardId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    bankCard = new BankCard(
                            resultSet.getString("number_card"),
                            resultSet.getString("cardholder_name"),
                            resultSet.getDate("expiration_date").toLocalDate(),
                            resultSet.getInt("cvv_number"),
                            resultSet.getLong("id_credit_card")

                                        );
                }
                logger.error(bankCard.toString());
            } catch (SQLException e) {
                logger.error("Une erreur s'est produite " +
                        "lors de la consultation de la carte en base de données", e);
            }
            return bankCard;
        }
    }





