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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@Remote(CarDao.class)
@Stateless
public class CarDaoImpl implements CarDao {
    private static final Logger logger= LogManager.getLogger();

    private static final String REQ_INSERT_CAR = "INSERT INTO car (id_model_car, id_user, license_plate_number, remove_date_car, registration_date_car, max_electric_power) VALUES (?,?,?,?,?,?)";
    private static final String REQ_FIND_BY_USER = "SELECT * FROM car c JOIN model_car mc ON c.id_model_car = mc.id_model_car JOIN brand_car bc ON mc.id_brand = bc.id_brand WHERE c.id_user = ?";
private final DataSource dataSource = new WattElseDataSource();

    @Override
    public void addCar(Car car, long userId, long idModelCar) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try {
                carStatementExecution(car, idModelCar, userId, connection);
                connection.commit();
                logger.info("{} a été inséré en base de données avec l'id {}", car.getIdCar(), car.getIdCar());
            } catch (SQLException e) {
                connection.rollback();
                logger.error("Une erreur s'est produite lors de l'insertion de {}", car.getIdCar(), e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
    }

    private Long getIdModelCar(Car car, Connection connection) {
        return null;
    }

    private void carStatementExecution(Car car, long idModelCar, long userId, Connection connection) throws SQLException, ParseException {
        PreparedStatement statement = connection.prepareStatement(REQ_INSERT_CAR, Statement.RETURN_GENERATED_KEYS);
        statement.setLong(1,idModelCar );
        statement.setLong(2,userId);
        statement.setLong(3,car.getIdCar());
        statement.setTimestamp(4, car.getRegistrationDateCar());
        statement.setTimestamp(5, car.getRemoveDateCar());;
        statement.setLong(6, car.getMaxElectricPower());
        int affectedRows = statement.executeUpdate();
        if (affectedRows > 0) {
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                long id = resultSet.getLong(1);
                car.setIdCar(id);
            }
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
    public int countByUser(long userId) {
        return 0;
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
           logger.error("Une erreur s'est produite lors de la consultation des voitures  en base de données", e);
       }
        return cars;
    }
}
