package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.BankCardDao;
import fr.eql.ai116.proj2.tim.dao.UserDao;
import fr.eql.ai116.proj2.tim.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.proj2.tim.entity.BankAccount;
import fr.eql.ai116.proj2.tim.entity.BankCard;
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
import java.util.Collections;
import java.util.List;

@Remote(BankCardDao.class)
@Stateless
public class BankCardDaoImpl implements BankCardDao {
    private final DataSource dataSource = new WattElseDataSource();
    private static final Logger logger = LogManager.getLogger();


    private static final String REQ_CLOSE_CARD = "UPDATE credit_card SET withdrawal_date_card = ? WHERE id_user = ?";
    private static final String REQ_ADD_CARD = "INSERT INTO credit_card (id_user, number_card, cardholder_name," +
            "expiration_date, cvv_number, registration_date_carte) VALUES (?,?,?,?,?,?) ";

    private static final String REQ_GET_ALL_USER_ACCOUNTS = "SELECT number_card, cardholder_name, expiration_date, cvv_number, id_credit_card " +
            " FROM credit_card cc JOIN session s ON cc.id_user = s.id_user WHERE s.token = ?";

    @Override
    public void addBankCard(BankCard bankCard, Long userId) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_ADD_CARD);
            statement.setLong(1, userId);
            statement.setString(2, bankCard.getNumberCard());
            statement.setString(3, bankCard.getCardHolderName());
            statement.setDate(4, Date.valueOf(bankCard.getExpirationDate()));
            statement.setLong(5,bankCard.getCvvNumber());
            statement.setTimestamp(6, Timestamp.from(Instant.now()));
            statement.executeUpdate();
            logger.info("Carte bancaire ajouté dans la base de données");
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
    }

    @Override
    public void closeBankCard(BankCard creditCard) {

    }

    @Override
    public void modifyBankCard(BankCard creditCard) {

    }

    @Override
    public List<BankCard> getBankCards(String token) {
        List<BankCard> cards = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_GET_ALL_USER_ACCOUNTS);
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

}


