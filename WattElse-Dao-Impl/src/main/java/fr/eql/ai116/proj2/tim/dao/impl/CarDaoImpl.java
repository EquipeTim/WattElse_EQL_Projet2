package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.CarDao;
import fr.eql.ai116.proj2.tim.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.proj2.tim.entity.Car;
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


@Remote(CarDao.class)
@Stateless
public class CarDaoImpl implements CarDao {
    private static final Logger logger= LogManager.getLogger();

    private static final String REQ_INSERT_CAR = "INSERT INTO car (id_model_car, id_user, " +
            "license_plate_number, registration_date_car, max_electric_power) " +
            "VALUES (?,?,?,?,?)";
    private static final String REQ_FIND_BY_USER = "SELECT * FROM car c WHERE c.id_user = ?";
    private static final String REQ_GET_MODEL_ID = "SELECT * FROM model_car mc JOIN brand_CAR bc " +
            "ON mc.id_brand = bc.id_brand WHERE brand_label = ? AND car_model_label = ?";

private final DataSource dataSource = new WattElseDataSource();

    @Override
    public void addCar(Car car, long userId) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try {
                Long modelId = getModelId(car.getBrand(), car.getCarModel(), connection);
                carAdditionExecution(car,userId, modelId, connection);
                connection.commit();
                logger.info("Voiture a été bien enregistré ");
            } catch (SQLException e) {
                connection.rollback();
                logger.error("Une erreur s'est produite lors de enregistrement de voiture " +
                        "pour utilisateur id {}", userId, e);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
    }

    private Long getModelId(String brand, String model, Connection connection) throws SQLException {
        Long modelId;
        PreparedStatement statement = connection.prepareStatement(REQ_GET_MODEL_ID);
        statement.setString(1, brand);
        statement.setString(2, model);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            modelId= resultSet.getLong("id_model_car");
        } else {
            modelId = getModelId("Autre", "Autre", connection);
        }
        return modelId;
    }

    private void carAdditionExecution(Car car, Long userId, Long modelId, Connection connection)
            throws SQLException{
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

    @Override
    public List<Car> findByUser(long userId) {
       List<Car> cars = new ArrayList<>();
       try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_BY_USER);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){

            }

        } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       return null;
    }
}
//@Override
//	public List<Cat> findByOwner(long ownerId) {
//		List<Cat> cats = new ArrayList<>();
//		try (Connection connection = dataSource.getConnection()) {
//			PreparedStatement statement = connection.prepareStatement(REQ_FIND_BY_OWNER);
//			statement.setLong(1, ownerId);
//			ResultSet resultSet = statement.executeQuery();
//			while(resultSet.next()) {
//				cats.add(new Cat(
//						resultSet.getLong("id"),
//						resultSet.getString("name"),
//						CatBreed.valueOf(resultSet.getString("breed")),
//						resultSet.getDate("birthdate").toLocalDate(),
//						resultSet.getString("picture")
//						)
//				);
//			}
//		} catch (SQLException e) {
//			logger.error("Une erreur s'est produite lors de la consultation des chats en base de données", e);
//		}
//		return cats;
//	}