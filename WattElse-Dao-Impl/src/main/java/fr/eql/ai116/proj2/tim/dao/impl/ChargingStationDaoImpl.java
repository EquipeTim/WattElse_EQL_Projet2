package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.ChargingStationDao;
import fr.eql.ai116.proj2.tim.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.proj2.tim.entity.Car;
import fr.eql.ai116.proj2.tim.entity.ChargingStation;
import fr.eql.ai116.proj2.tim.entity.OpeningHour;
import fr.eql.ai116.proj2.tim.entity.PlugType;
import fr.eql.ai116.proj2.tim.entity.PricingType;
import fr.eql.ai116.proj2.tim.entity.WeekDay;
import fr.eql.ai116.proj2.tim.entity.dto.ChargingStationDto;
import fr.eql.ai116.proj2.tim.entity.dto.ChoicesDto;
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
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

@Remote(ChargingStationDao.class)
@Stateless
public class ChargingStationDaoImpl implements ChargingStationDao{
    private static final Logger logger= LogManager.getLogger();
    private final DataSource dataSource = new WattElseDataSource();

private static final String REQ_GET_TERMINAL_OPENING_HOURS =
        "SELECT * FROM opening_hour oh " +
        "JOIN day d ON oh.id_day = d.id_day " +
        "JOIN charging_station cs ON oh.id_charging_station = cs.id_charging_station "  +
        "WHERE oh.id_charging_station = ? AND start_validity_date_opening_hour < ? " +
                "AND (end_validity_date_opening_hour > ? OR end_validity_date_opening_hour IS NULL) " +
                "AND cs.id_station_closing_type IS NULL";
private static final String REQ_FIND_TERMINAL =
        "SELECT * FROM charging_station cs " +
        "JOIN plug_type pt ON cs.id_plug_type = pt.id_plug_type " +
        "JOIN user u ON cs.id_user = u.id_user " +
        "JOIN city c ON c.id_city = cs.id_city " +
        "JOIN pricing p ON p.id_charging_station = cs.id_charging_station " +
        "JOIN pricing_type prt ON prt.id_type_pricing = p.id_type_pricing " +
        "JOIN opening_hour oh ON oh.id_charging_station = cs.id_charging_station " +
        "JOIN day d ON d.id_day = oh.id_day " +
        "WHERE " +
        "acos(sin(radians(latitude)) * sin(radians(?)) + cos(radians(latitude)) * " +
        "cos(radians(?)) * cos(radians(longitude) - radians(?))) * 6371 <= ? AND plug_type = ? " +
        "AND cs.closing_station_date IS NULL AND d.day = ? ";

private static final String REQ_GET_TERMINAL_BY_ID =
        "SELECT * FROM charging_station cs " +
        "JOIN plug_type pt ON cs.id_plug_type = pt.id_plug_type " +
        "JOIN user u ON cs.id_user = u.id_user " +
        "JOIN city c ON c.id_city = cs.id_city " +
        "JOIN pricing p ON p.id_charging_station = cs.id_charging_station " +
        "JOIN pricing_type prt ON prt.id_type_pricing = p.id_type_pricing " +
        "WHERE cs.id_charging_station = ?";

    @Override
    public List<ChargingStation> findChargingStation(Float centerLat, Float centerLong,
                                                     Integer radius, PlugType plug, String weekDay) {
        List<ChargingStation> stations = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_TERMINAL);
            statement.setFloat(1, centerLat);
            statement.setFloat(2, centerLat);
            statement.setFloat(3, centerLong);
            statement.setInt(4, radius);
            statement.setString(5, plug.name());
            statement.setString(6, weekDay);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String phone;
                if (resultSet.getString("emergency_phone") != null){
                    phone = resultSet.getString("emergency_phone");
                } else {
                    phone = resultSet.getString("phone_number");
                }
                stations.add(makeChargingStationObj(resultSet, phone));
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
    private ChargingStation makeChargingStationObj(ResultSet resultSet, String phone)
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
                return makeChargingStationObj(resultSet, phone);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
        return null;
    }

    @Override
    public List<OpeningHour> getOpeningHours(Long stationId, String timeZone) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of(timeZone));
        List<OpeningHour> openingHours = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_GET_TERMINAL_OPENING_HOURS);
            statement.setLong(1, stationId);
            statement.setString(2, String.valueOf(now));
            statement.setString(3, String.valueOf(now));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                LocalDate endDate;
                String day = resultSet.getString("day");
                String startHour = resultSet.getTime("start_hour").toString();
                String endHour = resultSet.getTime("end_hour").toString();
                LocalDate startDate = LocalDate.parse(resultSet.getDate("start_validity_date_opening_hour").toString());
                Date endDay = resultSet.getDate("end_validity_date_opening_hour");
                if (endDay == null){
                    endDate = null;
                } else {
                    endDate = LocalDate.parse(resultSet.getDate("end_validity_date_opening_hour").toString());
                }
                openingHours.add(new OpeningHour(day,LocalTime.parse(startHour),
                        LocalTime.parse(endHour),startDate,endDate));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
        return openingHours;
    }
}
