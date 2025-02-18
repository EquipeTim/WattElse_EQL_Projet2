package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.ComponentsDao;
import fr.eql.ai116.proj2.tim.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.proj2.tim.entity.Car;
import fr.eql.ai116.proj2.tim.entity.PlugType;
import fr.eql.ai116.proj2.tim.entity.dto.ChoicesDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Remote(ComponentsDao.class)
@Stateless

public class ComponentsDaoImpl implements ComponentsDao {
    private static final DataSource dataSource= new WattElseDataSource();
    private static final Logger logger = LogManager.getLogger();

    private static final String REQ_SELECT_PLUG = "SELECT * FROM plug_type ";
    private static final String REQ_GET_CAR_PLUGS = "SELECT * FROM model_car mc JOIN brand_car bc " +
            "ON mc.id_brand = bc.id_brand JOIN plug_type pt ON mc.id_plug_type = pt.id_plug_type " +
            "WHERE bc.brand_label = ? AND mc.car_model_label = ?";
    private static final String REQ_GET_CAR_BRANDS = "SELECT DISTINCT * FROM brand_car ORDER BY brand_label ASC";
    private static final String REQ_GET_MODELS = "SELECT DISTINCT car_model_label FROM model_car mc JOIN brand_car bc " +
            "ON mc.id_brand = bc.id_brand WHERE bc.brand_label = ? ORDER BY car_model_label ASC";
    private static final String REQ_GET_ACC_CLOSE_REASONS = "SELECT * FROM closing_account_user_type";
    private static final String REQ_GET_CAR_WITHDRAW_REASONS = "SELECT * FROM car_withdrawal_reason";
    private static final String REQ_GET_EVALUATION_TYPES = "SELECT * FROM evaluation_type";
    private static final String REQ_GET_PAYMENT_REFUSAL_REASONS = "SELECT * FROM payment_refuse_type";
    private static final String REQ_GET_PRICING_REASONS = "SELECT * FROM pricing_type";
    private static final String REQ_GET_RESERVATION_CANCEL_REASONS = "SELECT * FROM reservation_cancellation_type";
    private static final String REQ_GET_STATION_CLOSING_REASONS = "SELECT * FROM station_closing_type";
    private static final String REQ_GET_UNAVAILABILITY_REASONS = "SELECT * FROM unavailability_type";
    private static final String REQ_GET_WEEKDAY = "SELECT * FROM day";
    /**
     * Returns list of all plugs registered in DB
     * @return
     */
    @Override
    public List<ChoicesDto> getAllPlug() {
        return getList(REQ_SELECT_PLUG,
                "id_plug_type", "plug_type");
    }

    /**
     * Get plugs for this type of car
     * @param car
     * @return
     */
    @Override
    public List<ChoicesDto> findByModel(Car car) {
        List<ChoicesDto> carPlugs = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_GET_CAR_PLUGS);
            statement.setString(1, car.getBrand());
            statement.setString(2, car.getCarModel());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                carPlugs.add(new ChoicesDto(resultSet.getLong("id_plug_type"),
                        PlugType.valueOf(resultSet.getString("plug_type")).getDisplayName()));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
        return carPlugs;
    }

    /**
     * Return list of all Car brands registered in DB
     */
    @Override
    public List<ChoicesDto> getCarBrands() {
        return getList(REQ_GET_CAR_BRANDS,
                "id_brand", "brand_label");
    }

    /**
     * Get registered models in the DB associated to the brand
     * @param brand
     * @return
     */
    @Override
    public List<ChoicesDto> getCarModels(String brand) {
        return getListWithInput(REQ_GET_MODELS, null,
                "car_model_label", brand);
    }

    /**
     * Get list of request results with 1 search component
     * @param request
     * @param idColumn
     * @param labelColumn
     * @param searchTerm
     * @return
     */
    private List<ChoicesDto> getListWithInput(String request, String idColumn,
                                              String labelColumn, String searchTerm){
        List<ChoicesDto> choices = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setString(1, searchTerm);
            ResultSet resultSet = statement.executeQuery();
            Long id = 0L;
            while (resultSet.next()) {
                id = (idColumn != null) ? resultSet.getLong(idColumn) : ++id;
                choices.add(new ChoicesDto(id,
                        resultSet.getString(labelColumn)));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
        return choices;
    }

    private List<ChoicesDto> getList(String request, String idColumn, String labelColumn){
        List<ChoicesDto> choices = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                choices.add(new ChoicesDto(resultSet.getLong(idColumn),
                        resultSet.getString(labelColumn)));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
        return choices;
    }

    @Override
    public List<ChoicesDto> getAccountCloseReasons() {
        return getList(REQ_GET_ACC_CLOSE_REASONS,
                "id_label_closing_account_user", "label_closing_account_user");
    }

    @Override
    public List<ChoicesDto> getCarWithdrawalReasons() {
        return getList(REQ_GET_CAR_WITHDRAW_REASONS,
                "id_car_withdrawal", "car_withdrawal_label");
    }

    @Override
    public List<ChoicesDto> getEvaluationTypes() {
        return getList(REQ_GET_EVALUATION_TYPES,
                "id_type_evaluation", "evaluation_label");
    }

    @Override
    public List<ChoicesDto> getPaymentRefusalReasons() {
        return getList(REQ_GET_PAYMENT_REFUSAL_REASONS,
                "id_payment_refuse_type", "refuse_payment_label");
    }

    @Override
    public List<ChoicesDto> getPricingType() {
        return getList(REQ_GET_PRICING_REASONS,
                "id_type_pricing","type_pricing");
    }

    @Override
    public List<ChoicesDto> getReservationCancelType() {
        return getList(REQ_GET_RESERVATION_CANCEL_REASONS,
                "id_cancellation_type","cancellation_label");
    }

    @Override
    public List<ChoicesDto> getStationClosingType() {
        return getList(REQ_GET_STATION_CLOSING_REASONS,
                "id_station_closing_type","station_closing_type");
    }

    @Override
    public List<ChoicesDto> getUnavailabilityType() {
        return getList(REQ_GET_UNAVAILABILITY_REASONS,
                "id_unavailability_type", "unavailability_type");
    }

    @Override
    public List<ChoicesDto> getWeekDay() {
        return getList(REQ_GET_WEEKDAY,
                "id_day","day");
    }


}
