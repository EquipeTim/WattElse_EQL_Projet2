package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.UserDao;
import fr.eql.ai116.proj2.tim.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.proj2.tim.entity.*;
import fr.eql.ai116.proj2.tim.entity.dto.FullUserDto;
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
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Remote(UserDao.class)
@Stateless
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private final DataSource dataSource = new WattElseDataSource();

    private static final String REQ_AUTH = " SELECT * FROM user INNER JOIN city " +
            "ON user.id_city = city.id_city WHERE email = ? AND password = ? AND closing_date_account IS NULL";
    private static final String REQ_USER_EXISTS = "SELECT * FROM user WHERE email = ? and closing_date_account IS NULL";
    private static final String REQ_FIND_SESSION = "SELECT * FROM session WHERE token = ? ORDER BY timestamp DESC";
    private static final String REQ_UPDATE_SESSION = "INSERT INTO session (token, timestamp, id_user) VALUES (?,?,?) " +
            "ON DUPLICATE KEY UPDATE token = ?, timestamp = ?";
    private static final String REQ_ROLE_BY_ID_USER = "SELECT role FROM user WHERE id_user = ?";
    private static final String REQ_ADD_USER = "INSERT INTO user " +
            "(inscription_date_user, firstname_user, lastname_user, birthdate, phone_number, email, password, address_user, id_city, role) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String REQ_GET_CITY_ID = "SELECT id_city FROM city WHERE city = ? AND postal_code = ?";
    private static final String REQ_ADD_CITY = "INSERT INTO city (city, postal_code) VALUES (?,?)";

    private static final String REQ_CLOSE_ACC = "UPDATE user SET closing_date_account = ? ," +
            " id_label_closing_account_user = ? WHERE id_user = ?";
    private static final String REQ_IS_ACCOUNT_OWNER = "SELECT * FROM user u " +
            "JOIN session s ON u.id_user = s.id_user WHERE u.id_user = ? AND s.token = ?";
    private static final String REQ_FIND_USER_BY_ID ="SELECT * FROM user JOIN city " +
            "ON user.id_city = city.id_city WHERE id_user = ?";
    private static final String REQ_UPDATE_USER = "UPDATE user SET firstname_user = ?, lastname_user = ? , birthdate = ?" +
            ", phone_number = ? , email = ?, password = ?, address_user = ?, id_city = ? WHERE id_user = ?";
    private static  final String REQ_ACCOUNT_LOCKED_BY_ID = "SELECT * FROM user WHERE id_user =  ? " +
            "AND closing_date_account IS NOT NULL";
    private static  final String REQ_ACCOUNT_LOCKED_BY_EMAIL = "SELECT * FROM user WHERE email =  ? " +
            "AND closing_date_account IS NOT NULL";

    /**
     * Checks if the account is locked (closed) according to user ID; If user ID is null, check by e-mail
     * @param user
     * @return true if account associated to this email is closed
     */
    private boolean checkAccountLocked(User user, Connection connection) throws SQLException{
        boolean accountLocked = false;
        PreparedStatement statement;
        if (user.getId() == null) {
            statement = connection.prepareStatement(REQ_ACCOUNT_LOCKED_BY_EMAIL);
            statement.setString(1, user.getEmail());
        } else {
            statement = connection.prepareStatement(REQ_ACCOUNT_LOCKED_BY_ID);
            statement.setLong(1, user.getId());
        }
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            accountLocked = true;
        }
        return accountLocked;
    }


    /**
     * Registers user to database; Verifies if he does not exist, adds city if needed
     * @param user
     * @return true if user was added to DB
     */
    @Override
    public boolean registerUser(User user) {
        try (Connection connection = dataSource.getConnection()) {
            boolean allowToRegister = checkUserExists(user, connection);
            if (allowToRegister) {
                logger.info("Utilisateur avec email {} existe déjà", user.getEmail());
                return false;
            } else {
                connection.setAutoCommit(false);
                try {
                    Long cityId = getCityId(user);
                    addUser(user, cityId, connection);
                    connection.commit();
                    logger.info("{} a été inséré en base de données avec l'id {}", user.getName(), user.getId());
                    return true;
                } catch (SQLException e) {
                    connection.rollback();
                    logger.error("Une erreur s'est produite lors de l'insertion de utilisateur {}", user.getName(), e);
                }
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
        return false;
    }


    /**
     * Closes user account
     * @param userId
     * @return true if he if user closed successfully
     */
    @Override
    public boolean closeUserAccount(Long userId, Long closeReasonId) {
        try (Connection connection = dataSource.getConnection()) {
            Role role = findRoleByIdUser(userId);
            if (role == Role.USER) {
                PreparedStatement statement = connection.prepareStatement(REQ_CLOSE_ACC);
                statement.setTimestamp(1, Timestamp.from(Instant.now()));
                statement.setLong(2, closeReasonId);
                statement.setLong(3, userId);
                statement.executeUpdate();
                logger.info("La compte d'utilisateur {} a été fermé", userId);
                return true;
            } else {
                logger.info("Fermeture du compte admin pas permit");
                return false;
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
            return false;
        }
    }

    @Override
    public User getUserById(Long userId) {
        User user= null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_USER_BY_ID);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id_user"),
                        resultSet.getString("firstname_user"),
                        resultSet.getString("lastname_user"),
                        resultSet.getDate("birthdate").toLocalDate(),
                        resultSet.getString("email"),
                        resultSet.getString("address_user"),
                        resultSet.getString("city"),
                        resultSet.getString("postal_code"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("password"),
                        Role.valueOf(resultSet.getString("role"))

                );
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite " +
                    "lors de la consultation du chat en base de données", e);
        }
        return user;
    }

    @Override
    public boolean isAccountOwner(User user, String token) {
        boolean isAccountOwner = false;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_IS_ACCOUNT_OWNER);
            statement.setLong(1, user.getId());
            statement.setString(2, token);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isAccountOwner = true;
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite " +
                    "lors de la consultation du chat en base de données", e);
        }
        return isAccountOwner;
    }

    @Override
    public FullUserDto getUserData(String token) {
        FullUserDto fullUserDto = null;
        Session session = findSession(token);
        if (session != null){
            User user = getUserById(session.getUserId());
            fullUserDto = new FullUserDto(user.getName(),user.getSurname(),
                    user.getBirthDate(),user.getEmail(),null, user.getAddress(),
                user.getPhoneNumber(),user.getCity(),user.getPostCode());
        }
        return fullUserDto;
    }


    /**
     * Modifies user attributes within the DB
     * Allowed if Account is not locked and email does not change
     * OR
     * Account not Locked and the new email is not registered to Active user
     * @param newUser
     * @param token
     * @return
     */
    @Override
    public boolean modifyUser(User newUser, String token) {
        boolean success = false;
        Session session = findSession(token);
        if (session != null){
            User oldUser = getUserById(session.getUserId());
            try (Connection connection = dataSource.getConnection()) {
                connection.setAutoCommit(false);
                try {
                    newUser.setUserId(null); // needed in case to modify email to verify that new email was not used
                    if (Objects.equals(oldUser.getEmail(), newUser.getEmail()) ||
                            (!checkUserExists(newUser, connection)) ||
                        (checkUserExists(newUser, connection) && checkAccountLocked(newUser, connection) )) {
                        Long cityId = getCityId(newUser);
                        PreparedStatement statement = connection.prepareStatement(REQ_UPDATE_USER);
                        statement.setString(1, newUser.getName());
                        statement.setString(2, newUser.getSurname());
                        statement.setDate(3, Date.valueOf(newUser.getBirthDate()));
                        statement.setString(4, newUser.getPhoneNumber());
                        statement.setString(5, newUser.getEmail());
                        statement.setString(6, String.valueOf(newUser.getPassword().hashCode()));
                        statement.setString(7, newUser.getAddress());
                        statement.setLong(8, cityId);
                        statement.setLong(9, session.getUserId());

                        int affectedRows = statement.executeUpdate();
                        connection.commit();
                        if (affectedRows > 0) {
                            success = true;
                        }
                        logger.info("Les données d'utilisateur avec id {} a été bien modifié", session.getUserId());
                    }
                } catch (SQLException e) {
                    connection.rollback();
                    logger.error("Une erreur s'est produite lors de modification de utilisateur {}", newUser.getName(), e);
                }
            } catch (SQLException e) {
                logger.error("Une erreur s'est produite " +
                        "lors de la consultation du chat en base de données", e);
            }
        }
        return success;
    }




    /**
     * Checks if user exists in the databse according to his email
     * @param user
     * @param connection
     * @return true if he exists in DB
     */
    private boolean checkUserExists(User user, Connection connection) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(REQ_USER_EXISTS);
        statement.setString(1, user.getEmail());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds user to Database
     * @param user
     * @param cityId
     * @param connection
     * @throws SQLException
     */
    private void addUser(User user, Long cityId, Connection connection) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(REQ_ADD_USER, Statement.RETURN_GENERATED_KEYS);
        statement.setTimestamp(1, Timestamp.from(Instant.now()));
        statement.setString(2, user.getName());
        statement.setString(3, user.getSurname());
        statement.setDate(4, Date.valueOf(user.getBirthDate()));
        statement.setString(5, user.getPhoneNumber());
        statement.setString(6, user.getEmail());
        statement.setString(7, String.valueOf(user.getPassword().hashCode()));
        statement.setString(8, user.getAddress());
        statement.setLong(9, cityId);
        statement.setString(10, "USER");
        int affectedRows = statement.executeUpdate();
        if (affectedRows > 0) {
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                long id = resultSet.getLong(1);
                user.setUserId(id);
            }
        }
        logger.info("Utilisateur ajouté dans la base de données");
    }

    private Long getCityId(User user){
        Long cityId = null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_GET_CITY_ID);
            statement.setString(1, user.getCity());
            statement.setString(2, user.getPostCode());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cityId = resultSet.getLong("id_city");
            } else {
                cityId = addCity(user, connection);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite " +
                    "lors de la consultation des villes en base de données", e);
        }

        return cityId;
    }

    private Long addCity(User user, Connection connection)  throws SQLException{
        Long cityId = null;
        PreparedStatement statement = connection.prepareStatement(REQ_ADD_CITY, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.getCity());
        statement.setString(2, user.getPostCode());
        int affectedRows = statement.executeUpdate();
        if (affectedRows > 0) {
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                cityId = resultSet.getLong(1);
            }
        }
        return cityId;
    }

    @Override
    public User authenticate(String email, String password) {
        User user = null;
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(REQ_AUTH);
            statement.setString(1, email);
            statement.setString(2, String.valueOf(password.hashCode()));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id_user"),
                        resultSet.getString("firstname_user"),
                        resultSet.getString("lastname_user"),
                        resultSet.getDate("birthdate").toLocalDate(),
                        resultSet.getString("email"),
                        resultSet.getString("address_user"),
                        resultSet.getString("city"),
                        resultSet.getString("postal_code"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("password"),
                        Role.valueOf(resultSet.getString("role")
                        )
                );
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite " +
                    "lors de la consultation du propriétaire en base de données", e);
        }
        return user;
    }

    @Override
    public Session findSession(String token) {
        Session session = null;
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_SESSION);
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                session = new Session(
                        resultSet.getLong("id_session"),
                        resultSet.getString("token"),
                        resultSet.getTimestamp("timestamp"),
                        resultSet.getLong("id_user")
                );
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite " +
                    "lors de la consultation de la session utilisateur en base de données", e);
        }
        return session;
    }

    @Override
    public void updateSession(String token, long userId) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(REQ_UPDATE_SESSION);
            statement.setString(1, token);
            statement.setTimestamp(2, Timestamp.from(Instant.now()));
            statement.setLong(3, userId);
            statement.setString(4, token);
            statement.setTimestamp(5, Timestamp.from(Instant.now()));
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite " +
                    "lors de la mise à jour de la session utilisateur en base de données", e);
        }
    }

    @Override
    public Role findRoleByIdUser(long userId) {
        Role role = null;
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(REQ_ROLE_BY_ID_USER);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                role = Role.valueOf(resultSet.getString("role"));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite " +
                    "lors de la consultation du rôle du propriétaire en base de données", e);
        }
        return role;
    }

}
