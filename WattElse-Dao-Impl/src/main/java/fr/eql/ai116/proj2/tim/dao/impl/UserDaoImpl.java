package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.UserDao;
import fr.eql.ai116.proj2.tim.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.proj2.tim.entity.Role;
import fr.eql.ai116.proj2.tim.entity.Session;
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
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;

@Remote(UserDao.class)
@Stateless
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger();

    //private static final String REQ_AUTH = " SELECT * FROM user WHERE email = ? AND password = ?";
    private static final String REQ_AUTH = " SELECT * FROM user INNER JOIN city " +
            "ON user.id_city = city.id_city WHERE email = ? AND password = ?";
    private static final String REQ_FIND_SESSION = "SELECT * FROM session WHERE token = ?";
    private static final String REQ_UPDATE_SESSION = "INSERT INTO session (token, timestamp, id_user) VALUES (?,?,?) " +
            "ON DUPLICATE KEY UPDATE token = ?, timestamp = ?";
    private static final String REQ_ROLE_BY_ID_USER = "SELECT role FROM owner WHERE id = ?";
    private static final String REQ_ADD_USER = "INSERT INTO user " +
            "(inscription_date_user, firstname_user, lastname_user, birthdate, phone_number, email, password, address_user, id_city) VALUES (?,?,?)";
    private static final String REQ_GET_CITY_ID = "SELECT id FROM city WHERE city = ? AND postal_code = ?";
    private static final String REQ_ADD_CITY = "INSERT INTO city (city, postal_code) VALUES (?,?)";


    private final DataSource dataSource = new WattElseDataSource();

    @Override
    public void registerUser(User user) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try {
                Long cityId = getCityId(user, connection);
                addUser(user, cityId, connection);
                connection.commit();
                logger.info("{} a été inséré en base de données avec l'id {}", user.getName(), user.getId());
            }
            catch (SQLException e) {
                connection.rollback();
                logger.error("Une erreur s'est produite lors de l'insertion de utilisateur {}", user.getName(), e);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
    }

    private void addUser(User user, Long cityId, Connection connection) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(REQ_ADD_USER, Statement.RETURN_GENERATED_KEYS);
        statement.setTimestamp(1, Timestamp.from(Instant.now()));
        statement.setString(2, user.getName());
        statement.setString(3, user.getSurname());
        statement.setDate(4, Date.valueOf(user.getBirthDate()));
        statement.setString(5, user.getPhoneNumber());
        statement.setString(6, user.getEmail());
        statement.setString(7, user.getPassword());
        statement.setString(8, user.getAddress());
        statement.setLong(9, cityId);
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

    private Long getCityId(User user, Connection connection) throws SQLException{
        Long cityId = null;
        PreparedStatement statement = connection.prepareStatement(REQ_GET_CITY_ID);
        statement.setString(1, user.getCity());
        statement.setString(2, user.getPostCode());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            cityId = resultSet.getLong("id");
        } else {
            cityId = addCity(user, connection);
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
            statement.setString(2, password);
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
                        resultSet.getLong("id"),
                        resultSet.getString("token"),
                        resultSet.getTimestamp("timestamp"),
                        resultSet.getLong("user_id")
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
