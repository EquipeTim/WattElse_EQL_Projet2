package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.ChargingStationDao;
import fr.eql.ai116.proj2.tim.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.proj2.tim.entity.Car;
import fr.eql.ai116.proj2.tim.entity.ChargingStation;
import fr.eql.ai116.proj2.tim.entity.PlugType;
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
private final String REQ_FIND_TERMINAL = "SELECT * FROM charging_station cs JOIN plug_type pt ON " +
        "cs.id_plug_type = pt.id_plug_type JOIN user u ON cs.id_user = u.id_user WHERE " +
        "acos(sin(radians(latitude)) * sin(radians(?)) + cos(radians(latitude)) * " +
        "cos(radians(?)) * cos(radians(longitude) - radians(?))) * 6371 <= ? AND plug_type = ?";

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
            statement.setString(5, String.valueOf(plug));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String phone;
                if (resultSet.getString("emergency_phone") != null){
                    phone = resultSet.getString("emergency_phone");
                } else {
                    phone = resultSet.getString("phone_number");
                }
                stations.add(new ChargingStation(resultSet.getLong("id_charging_station"),
                        resultSet.getLong("power_charging_station"),
                        resultSet.getString("address_charging_station"),
                        resultSet.getLong("longitude"),
                        resultSet.getLong("latitude"),
                        phone,
                        PlugType.valueOf(resultSet.getString("plug_type"))));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stations;
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
    public List<ChargingStation> getChargingStations(long userId) {
        return null;
    }
}
