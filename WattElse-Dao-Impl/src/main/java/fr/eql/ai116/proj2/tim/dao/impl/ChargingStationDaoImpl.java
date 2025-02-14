package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.ChargingStationDao;
import fr.eql.ai116.proj2.tim.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.proj2.tim.entity.ChargingStation;
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
import java.util.List;

//@Remote est une annotation utilisée pour spécifier que l'interface d'un EJB sera exposée à des clients distants
//@Remote(ChargingStationDao.class)
//@Stateless // annotation utilisée pour définir un EJB sans état (stateless session bean).Les beans sans état sont utilisés pour des opérations courtes et indépendantes qui ne nécessitent
// pas de stockage d'informations spécifiques sur les sessions des utilisateurs
//
//
//public class ChargingStationDaoImpl implements ChargingStationDao{
//    private static final Logger logger= LogManager.getLogger();
//    //Rquest//
//
//    private static final String REQ_INSERT_STATION = "INSERT INTO charging_station (id_charging_station, id_station_closing_type, id_plug_type,id_city, id_user, " +
//            "power_charging_station,registration_station_date, closing_station_date,address_charging_station, longitude, latitude, emergency_phone) VALUES (?,?,?,?,?,?," +
//            "?,?,?,?,?,?)";
//
//    //DataSource object to get a connection to database///
//    private final DataSource dataSource = new WattElseDataSource();
//
//
//    @Override
//    public void addChargingStation(ChargingStation chargingStation, long userId) {
//        //Connection connection = dataSource.getConnection() : crée une connexion à la base de données à partir du dataSource et la lie à la variable connection
//        // Grâce à try-with-resources, on s'assure que la connexion sera fermée de manière fiable sans fuites de connexion
//        try(Connection connection = dataSource.getConnection()){
//            //Cette ligne désactive le mode de validation automatique (auto-commit) de la connexion à la base de données.
//            //Par défaut, lorsqu'une connexion est établie, le mode auto-commit est activé.
//           //auto-commit activé :
//            //Exécution d'une requête INSERT -> La requête est immédiatement validée et enregistrée dans la base de données sans nécessiter d'autres étapes.
//            connection.setAutoCommit(false);
//            try {
//                chargingStationStatementExecution(chargingStation, userId, connection);
//                connection.commit();
//                logger.info("{} a été inséré en base de données avec l'id {}",chargingStation.getIdChargingStation());
//            } catch (SQLException e) {
//                // pour annuler la transaction en cours
//                connection.rollback();
//                logger.error("une erreur s'est produite lors de l'insertion de {}",chargingStation.getIdChargingStation(),e);
//                throw new RuntimeException(e);
//            }
//
//        } catch (SQLException e) {
//            logger.error("une erreur s'est produite lors de la connexion avec la base de données", e);
//        }
//    }
//
////    private void chargingStationStatementExecution(ChargingStation chargingStation,
//////                  long userId, Connection connection) throws SQLException{
//////        PreparedStatement statement = connection.prepareStatement(REQ_INSERT_STATION, Statement.RETURN_GENERATED_KEYS);
//////        statement.setLong(1, chargingStation.getIdChargingStation());
//////        statement.setLong(2,  );
//////        statement.setLong(3,car.getLicensePlateNumber());
//////        statement.setDate(4, Date.valueOf(car.getRegistrationDateCar()));
//////        statement.setDate(5, Date.valueOf(car.getRemoveDateCar()));
//////        statement.setLong(6, car.getMaxElectricPower());
//////        int affectedRows = statement.executeUpdate();
//////        if (affectedRows > 0) {
//////            ResultSet resultSet = statement.getGeneratedKeys();
//////            if (resultSet.next()) {
//////                long id = resultSet.getLong(1);
//////                car.setIdCar(id);
////            }
////        }
//
//    }
//
//    @Override
//    public void removeChargingStation(ChargingStation chargingStation) {
//
//    }
//
//    @Override
//    public void modifyChargingStation(ChargingStation chargingStation) {
//
//    }
//
//    @Override
//    public List<ChargingStation> getChargingStations(long userId) {
//        return null;
//    }
//}
