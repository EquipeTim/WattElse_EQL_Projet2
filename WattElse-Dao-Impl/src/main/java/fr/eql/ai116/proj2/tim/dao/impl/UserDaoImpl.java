package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.UserDao;
import fr.eql.ai116.proj2.tim.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.proj2.tim.entity.Role;
import fr.eql.ai116.proj2.tim.entity.Session;
import fr.eql.ai116.proj2.tim.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String REQ_AUTH = " SELECT * FROM user WHERE email = ? AND password = ?";
    private static final String REQ_FIND_SESSION = "SELECT * FROM session WHERE token = ?";
    private static final String REQ_UPDATE_SESSION = "INSERT INTO session (token, timestamp, id_user) VALUES (?,?,?) " +
            "ON DUPLICATE KEY UPDATE token = ?, timestamp = ?";
    private static final String REQ_ROLE_BY_ID_USER = "SELECT role FROM owner WHERE id = ?";

    private final DataSource dataSource = new WattElseDataSource();

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
                        resultSet.getString("email"),
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
    public Role findRoleByIdOwner(long userId) {
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
