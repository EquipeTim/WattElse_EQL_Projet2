package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.BankAccountDao;
import fr.eql.ai116.proj2.tim.dao.BankCardDao;
import fr.eql.ai116.proj2.tim.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.proj2.tim.entity.BankAccount;
import fr.eql.ai116.proj2.tim.entity.Session;
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

@Remote(BankAccountDao.class)
@Stateless
public class BankAccountDaoImpl  implements BankAccountDao {
    private final DataSource dataSource = new WattElseDataSource();
    private static final Logger logger = LogManager.getLogger();

    private static final String REQ_CLOSE_BANK_ACC = "UPDATE bank_account SET closing_date_bank_account = ? WHERE id_user = ?";
    private static final String REQ_ADD_BANK_ACC = "INSERT INTO bank_account (id_user, iban, account_owner_name," +
            "bic_swift, account_registration_date) VALUES (?,?,?,?,?) ";
    private static final String REQ_GET_ALL_USER_ACCOUNTS = "SELECT account_owner_name, bic_swift, id_bank_account, " +
            "RIGHT(iban, 4) as iban FROM bank_account ba JOIN session s ON ba.id_user = s.id_user WHERE s.token = ?";

    @Override
    public void addBankAccount(BankAccount bankAccount, Long userId) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_ADD_BANK_ACC);
            statement.setLong(1, userId);
            statement.setString(2, bankAccount.getIban());
            statement.setString(3, bankAccount.getAccountOwnerName());
            statement.setString(4, bankAccount.getBicSwift());
            statement.setTimestamp(5, Timestamp.from(Instant.now()));
            statement.executeUpdate();
            logger.info("Compte bancaire ajouté dans la base de données");
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
    }

    @Override
    public void closeBankAccount(BankAccount bankAccount) {

    }

    @Override
    public void modifyBankAccount(BankAccount bankAccount) {

    }

    @Override
    public List<BankAccount> getBankAccounts(String token) {
        List<BankAccount> accounts = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_GET_ALL_USER_ACCOUNTS);
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                accounts.add(new BankAccount(resultSet.getString("iban"),
                                            resultSet.getString("account_owner_name"),
                                            resultSet.getString("bic_swift"),
                                            resultSet.getLong("id_bank_account")));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
        return accounts;
    }
}
