package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.CarDao;
import fr.eql.ai116.proj2.tim.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.proj2.tim.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Remote(CarDao.class)
@Stateless
public class CarDaoImpl implements CarDao {
    private static final Logger logger = LogManager.getLogger();
    private final DataSource dataSource = new WattElseDataSource();

    private static final String REQ_INSERT_CAR = "INSERT INTO car (id_model_car, id_user, " +
            "license_plate_number, registration_date_car, max_electric_power) " +
            "VALUES (?,?,?,?,?)";
    private static final String REQ_CHECK_CAR_EXISTS = "SELECT * FROM car " +
            "WHERE license_plate_number = ? AND id_user = ?";

    private static final String REQ_FIND_BY_USER = "SELECT * FROM car c JOIN model_car mc ON " +
            "c.id_model_car = mc.id_model_car JOIN brand_car bc ON mc.id_brand = bc.id_brand " +
            "JOIN plug_type pt ON mc.id_plug_type = pt.id_plug_type JOIN session s ON " +
            "c.id_user = s.id_user WHERE s.token = ?";
    private static final String REQ_GET_MODEL_ID = "SELECT * FROM model_car mc JOIN brand_CAR bc " +
            "ON mc.id_brand = bc.id_brand JOIN plug_type pt ON mc.id_plug_type = pt.id_plug_type " +
            "WHERE brand_label = ? AND car_model_label = ? AND plug_type = ?";
    private static final String REQ_GET_MODEL_ID_PLUG_ONLY = "SELECT * FROM model_car mc " +
            "JOIN plug_type pt ON mc.id_plug_type = pt.id_plug_type WHERE pt.plug_type = ? " +
            "AND car_model_label = 'Autre'";



    @Override
    public boolean addCar(Car car, long userId) {
        boolean success = false;
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            boolean exists = checkCarExists(car.getLicensePlate(), userId, connection);
            if (!exists) {
                try {
                    Long modelId = getModelId(car, connection);
                    carAdditionExecution(car, userId, modelId, connection);
                    connection.commit();
                    logger.info("Voiture a été bien enregistré ");
                    success = true;
                } catch (SQLException e) {
                    connection.rollback();
                    logger.error("Une erreur s'est produite lors de enregistrement de voiture " +
                            "pour utilisateur id {}", userId, e);
                }
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
        return success;
    }

    /**
     * Checks if car is already added to database by the same owner
     * according to the license plate and userId
     *
     * @param licensePlate
     * @param connection   Active connection to DB
     * @return true if car is already registered
     * @throws SQLException
     */
    private boolean checkCarExists(String licensePlate, Long userId, Connection connection) throws SQLException {
        boolean exists = true;
        PreparedStatement statement = connection.prepareStatement(REQ_CHECK_CAR_EXISTS);
        statement.setString(1, licensePlate);
        statement.setLong(2, userId);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) {
            exists = false;
        }
        return exists;
    }

    /**
     * Gets model id from model_car table. If doesnt exists, assign value for "other"
     * get model ID from
     *
     * @param car        Car object
     * @param connection Active connection to DB
     * @return
     * @throws SQLException
     */
    private Long getModelId(Car car, Connection connection) throws SQLException {
        Long modelId;
        PreparedStatement statement = connection.prepareStatement(REQ_GET_MODEL_ID);
        statement.setString(1, car.getBrand());
        statement.setString(2, car.getCarModel());
        statement.setString(3, car.getPlug());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            modelId = resultSet.getLong("id_model_car");
        } else {
            modelId = getModelIdByPlugOnly(car.getPlug(), connection);
        }
        return modelId;
    }

    /**
     * Get model_ID assuming car brand and model unknown
     * and only using plug type
     *
     * @param plug       Splug label of the car
     * @param connection Active connection to DB
     * @return model id associated to plug for unknown car
     */
    private Long getModelIdByPlugOnly(String plug, Connection connection) throws SQLException {
        Long modelId;
        PreparedStatement statement = connection.prepareStatement(REQ_GET_MODEL_ID_PLUG_ONLY);
        statement.setString(1, plug);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            modelId = resultSet.getLong("id_model_car");
        } else {
            modelId = getModelIdByPlugOnly("Autre", connection);
        }
        return modelId;
    }

    /**
     * Adds car to Database
     *
     * @param car
     * @param userId
     * @param modelId
     * @param connection Active connection to DB
     * @throws SQLException
     */
    private void carAdditionExecution(Car car, Long userId, Long modelId, Connection connection)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(REQ_INSERT_CAR);
        statement.setLong(1, modelId);
        statement.setLong(2, userId);
        statement.setString(3, car.getLicensePlate());
        statement.setTimestamp(4, Timestamp.from(Instant.now()));
        statement.setLong(5, car.getMaxElectricPower());
        statement.executeUpdate();
    }

    @Override
    public boolean exists(Car car) {
        return false;
    }

    @Override
    public void removeCar(Car car) {

    }

    @Override
    public void modifyCar(Car car) {

    }

    /**
     * GEt vehicles owned by user
     *
     * @param token
     * @return List of cars owned by user
     */
    @Override
    public List<Car> findByUser(String token) {
        List<Car> cars = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_BY_USER);
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cars.add(new Car(resultSet.getLong("id_car"),
                        resultSet.getString("car_model_label"),
                        resultSet.getString("brand_label"),
                        resultSet.getLong("max_electric_power"),
                        resultSet.getString("license_plate_number"),
                        resultSet.getString("plug_type")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }

}
