package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.BankAccountDao;
import fr.eql.ai116.proj2.tim.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.proj2.tim.entity.BankAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Remote(BankAccountDao.class)
@Stateless
public class BankAccountDaoImpl  implements BankAccountDao {
    private final DataSource dataSource = new WattElseDataSource();
    private static final Logger logger = LogManager.getLogger();

    private static final String REQ_CLOSE_BANK_ACC = "UPDATE bank_account SET account_close_date = ? WHERE id_bank_account = ?";
    private static final String REQ_FIND_ACCOUNT_BY_ID = "SELECT * FROM bank_account WHERE id_bank_account = ?";
    private static final String REQ_ADD_BANK_ACC = "INSERT INTO bank_account (id_user, iban, account_owner_name," +
            "bic_swift, account_registration_date) VALUES (?,?,?,?,?) ";
    private static final String REQ_GET_ALL_USER_ACCOUNTS = "SELECT account_owner_name, bic_swift, id_bank_account, " +
            "RIGHT(iban, 4) AS iban FROM bank_account ba JOIN session s ON ba.id_user = s.id_user WHERE s.token = ?";
    private static final String REQ_ACCOUNT_EXISTS = "SELECT * FROM bank_account WHERE iban = ? and id_user =? and account_close_date IS NULL";



    private void addBankAccount(BankAccount bankAccount, Long userId, Connection connection) throws SQLException{

            PreparedStatement statement = connection.prepareStatement(REQ_ADD_BANK_ACC);
            statement.setLong(1, userId);
            statement.setString(2, bankAccount.getIban());
            statement.setString(3, bankAccount.getAccountOwnerName());
            statement.setString(4, bankAccount.getBicSwift());
            statement.setTimestamp(5, Timestamp.from(Instant.now()));
            statement.executeUpdate();
            logger.info("Compte bancaire ajouté dans la base de données");
     }


    /**
     * Checks if user exists in the databse according to his email
     * @param bankAccount
     * @param connection
     * @return true if he exists in DB
     */
    private boolean checkAccountExists(BankAccount bankAccount, Long userId, Connection connection) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(REQ_ACCOUNT_EXISTS);
        statement.setString(1, bankAccount.getIban());
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
     * @param bankAccount
     * @return true if account was added to DB
     */
    @Override
    public boolean registerBankAccount(BankAccount bankAccount, Long userId) {
    try (Connection connection = dataSource.getConnection()) {
        boolean allowToRegister = checkAccountExists(bankAccount, userId, connection);
        if (allowToRegister) {
            logger.info("carte avec l'IBAN {} existe déjà", bankAccount.getIban());
            return false;
        } else {
            connection.setAutoCommit(false);
            addBankAccount(bankAccount, userId, connection);
            connection.commit();
            return true;
        }
    } catch (SQLException e) {
        logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
    }
     return false;
}

    /**
     * Closes bank account
     * @param bankAccountId
     * @return
     */
    @Override
    public boolean closeBankAccount(Long bankAccountId) {
        try(Connection connection =  dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(REQ_CLOSE_BANK_ACC);
            statement.setTimestamp(1, Timestamp.from(Instant.now())); // to current date : 1er parametre pour donner une date à la fermeture
            statement.setLong(2,bankAccountId);
            int rowsAffected = statement.executeUpdate();// Executes the prepared SQL query (the update) and returns the number of rows affected
            // (e.g., 1 if one account was updated, 0 if no account was found matching the bankAccountId
            if(rowsAffected > 0) {
                logger.info("Account with ID {} successfully closed", bankAccountId);
                return true;
            }else{
                logger.warn("No account found with ID {}", bankAccountId);
                return false;
            }
        } catch (SQLException e) {
            logger.error("An error occurred while closing the card with ID {}", bankAccountId, e);
            return false;
        }

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

    @Override
    public BankAccount getBankAccountById(Long idBankAccount) {
        BankAccount bankAccount = null;
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_ACCOUNT_BY_ID);
            statement.setLong(1, idBankAccount);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next()){
                bankAccount = new BankAccount(
                        resultSet.getString("iban"),
                        resultSet.getString("account_owner_name"),
                        resultSet.getString("bic_swift"),
                        resultSet.getLong("id_bank_account")
                );
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation du compte en base de données", e);
        }
        return bankAccount;
    }


}

