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
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Remote(CarDao.class)
@Stateless
public class CarDaoImpl implements CarDao {
    private static final Logger logger= LogManager.getLogger();

    private static final String REQ_INSERT_CAR = "INSERT INTO car (id_model_car, id_user, license_plate_number, remove_date_car, registration_date_car, max_electric_power) VALUES (?,?,?,?,?,?)";
    private static final String REQ_FIND_BY_USER = "SELECT * FROM car c WHERE c.id_user = ?";
private final DataSource dataSource = new WattElseDataSource();

    @Override
    public void addCar(Car car, long userId) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_INSERT_CAR);
            statement.setLong(1, car.getIdModelCar());
            statement.setLong(2, userId);
            statement.setString(3, car.getLicensePlate());
            statement.setTimestamp(4, Timestamp.from(Instant.now()));
            statement.setLong(5, car.getMaxElectricPower());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
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