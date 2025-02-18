package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.ChargingStationDao;
import fr.eql.ai116.proj2.tim.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.proj2.tim.entity.Car;
import fr.eql.ai116.proj2.tim.entity.ChargingStation;
import fr.eql.ai116.proj2.tim.entity.PlugType;
import fr.eql.ai116.proj2.tim.entity.PricingType;
import fr.eql.ai116.proj2.tim.entity.dto.ChargingStationDto;
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
import java.util.ArrayList;
import java.util.List;

@Remote(ChargingStationDao.class)
@Stateless
public class ChargingStationDaoImpl implements ChargingStationDao{
    private static final Logger logger= LogManager.getLogger();
    private final DataSource dataSource = new WattElseDataSource();

//    private static final String REQ_FIND_TERMINAL = "SELECT * FROM charging_station cs JOIN plug_type pt ON " +
//            "cs.id_plug_type = pt.id_plug_type JOIN user u ON cs.id_user = u.id_user WHERE " +
//            "ST_DWithin(geography(POINT(longitude, latitude)), " +
//            "geography(POINT(?, ?)), ? * 1000) AND plug_type = ?";
private static final String REQ_FIND_TERMINAL = "SELECT * FROM charging_station cs " +
        "JOIN plug_type pt ON cs.id_plug_type = pt.id_plug_type " +
        "JOIN user u ON cs.id_user = u.id_user " +
        "JOIN city c ON c.id_city = cs.id_city " +
        "JOIN pricing p ON p.id_charging_station = cs.id_charging_station " +
        "JOIN pricing_type prt ON prt.id_type_pricing = p.id_type_pricing " +
        "WHERE " +
        "acos(sin(radians(latitude)) * sin(radians(?)) + cos(radians(latitude)) * " +
        "cos(radians(?)) * cos(radians(longitude) - radians(?))) * 6371 <= ? AND plug_type = ?";

private static final String REQ_GET_TERMINAL_BY_ID = "SELECT * FROM charging_station cs " +
            "JOIN plug_type pt ON cs.id_plug_type = pt.id_plug_type " +
            "JOIN user u ON cs.id_user = u.id_user " +
            "JOIN city c ON c.id_city = cs.id_city " +
            "JOIN pricing p ON p.id_charging_station = cs.id_charging_station " +
            "JOIN pricing_type prt ON prt.id_type_pricing = p.id_type_pricing " +
            "WHERE cs.id_charging_station = ?";

    @Override
    public List<ChargingStation> findChargingStation(Float centerLat, Float centerLong,
                                                     Integer radius, PlugType plug) {
        List<ChargingStation> stations = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_TERMINAL);
            statement.setFloat(1, centerLat);
            statement.setFloat(2, centerLat);
            statement.setFloat(3, centerLong);
            statement.setInt(4, radius);
            statement.setString(5, plug.name());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String phone;
                if (resultSet.getString("emergency_phone") != null){
                    phone = resultSet.getString("emergency_phone");
                } else {
                    phone = resultSet.getString("phone_number");
                }
                stations.add(buildChargingStation(resultSet, phone));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
        return stations;
    }

    /**
     * Builds DTO object from resultset
     * @param resultSet
     * @param phone
     * @return
     * @throws SQLException
     */
    private ChargingStation buildChargingStation(ResultSet resultSet, String phone)
                                                throws SQLException{
        return new ChargingStation(
                resultSet.getLong("id_charging_station"),
                resultSet.getString("city"),
                resultSet.getInt("power_charging_station"),
                resultSet.getString("address_charging_station"),
                resultSet.getFloat("longitude"),
                resultSet.getFloat("latitude"),
                phone,
                PlugType.valueOf(resultSet.getString("plug_type")).getDisplayName(),
                PricingType.valueOf(resultSet.getString("type_pricing")).getLabel(),
                resultSet.getFloat("price"));
    }

    @Override
    public void addChargingStation(ChargingStation chargingStation, long userId) {

    }

    @Override
    public void removeChargingStation(ChargingStation chargingStation) {

    }

    @Override
    public void modifyChargingStation(ChargingStation chargingStation) {

    }

    @Override
    public ChargingStation getChargingStationById(long stationId){
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_GET_TERMINAL_BY_ID);
            statement.setLong(1, stationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String phone;
                if (resultSet.getString("emergency_phone") != null){
                    phone = resultSet.getString("emergency_phone");
                } else {
                    phone = resultSet.getString("phone_number");
                }
                return buildChargingStation(resultSet, phone);
            }

        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
        return null;
    }
}
