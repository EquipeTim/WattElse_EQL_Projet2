package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.EnumLoadingDao;
import fr.eql.ai116.proj2.tim.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.proj2.tim.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

@Remote(EnumLoadingDao.class)
@Stateless
public class EnumLoadingDaoImpl implements EnumLoadingDao {
    private static final Logger logger = LogManager.getLogger();
    private final DataSource dataSource = new WattElseDataSource();

    private static final String REQ_GET_ALL_CAR_BRANDS = "SELECT brand_label FROM brand_car";
    private static final String REQ_INSERT_CAR_BRANDS = "INSERT INTO brand_car (brand_label) VALUES (?)";

    private static final String REQ_SELECT_ALL_CARS_MODELS =
            "SELECT * FROM model_car mc JOIN brand_car bc ON mc.id_brand = bc.id_brand JOIN plug_type " +
                    "pt ON mc.id_plug_type = pt.id_plug_type";
    private static final String REQ_INSERT_CAR_MODELS =
            "INSERT INTO model_car (id_plug_type, id_brand, car_model_label) VALUES (?,?,?)";
    private static final String REQ_GET_PLUG_ID = "SELECT * FROM plug_type WHERE plug_type = ?";
    private static final String REQ_GET_BRAND_ID = "SELECT * FROM brand_car WHERE brand_label = ?";

    private static final String REQ_GET_CAR_WITHDRAWAL_REASONS = "SELECT * FROM car_withdrawal_reason";
    private static final String REQ_INSERT_CAR_WITHDRAW_REASONS = "INSERT INTO car_withdrawal_reason (car_withdrawal_label) " +
            "VALUES (?)";

    private static final String REQ_GET_ALL_PLUG_TYPES = "SELECT plug_type FROM plug_type";
    private static final String REQ_INSERT_PLUG_TYPE = "INSERT INTO plug_type (plug_type) VALUES (?)";

    private static final String REQ_GET_ALL_ACC_CLOSE_REASONS =
            "SELECT label_closing_account_user FROM closing_account_user_type";
    private static final String REQ_INSERT_ACC_CLOSE_REASONS =
            "INSERT INTO closing_account_user_type (label_closing_account_user) VALUES (?)";

    private static final String REQ_GET_ALL_EVAL_TYPES = "SELECT * FROM evaluation_type";
    private static final String REQ_GET_ALL_REFUSAL_TYPES = "SELECT * FROM payment_refuse_type";
    private static final String REQ_GET_ALL_PRICING_TYPES = "SELECT * FROM pricing_type";
    private static final String REQ_GET_ALL_RESERVATION_CANCEL_TYPES = "SELECT * FROM reservation_cancellation_type";
    private static final String REQ_GET_ALL_STATION_CLOSING_TYPES = "SELECT * FROM station_closing_type";
    private static final String REQ_GET_ALL_STATION_UNAVAILABILITY_TYPES = "SELECT * FROM unavailability_type";
    private static final String REQ_GET_WEEKDAYS = "SELECT * FROM day";
    private static final String REQ_INSERT_EVAL_TYPE = "INSERT INTO evaluation_type (evaluation_label) VALUES (?)";
    private static final String REQ_INSERT_REFUSAL_TYPE = "INSERT INTO payment_refuse_type (refuse_payment_label) VALUES (?)";
    private static final String REQ_INSERT_PRICING_TYPE = "INSERT INTO pricing_type (type_pricing) VALUES (?)";
    private static final String REQ_INSERT_RESERVATION_CANCEL_TYPE = "INSERT INTO reservation_cancellation_type (cancellation_label) VALUES (?)";
    private static final String REQ_INSERT_STATION_CLOSING_TYPE = "INSERT INTO station_closing_type (station_closing_type) VALUES (?)";
    private static final String REQ_INSERT_STATION_UNAVAILABILITY_TYPE = "INSERT INTO unavailability_type (unavailability_type) VALUES (?)";
    private static final String REQ_INSERT_WEEKDAYS = "INSERT INTO day (day) VALUES (?)";


    /**
     * Load String to DB table (For example reason, car brands)
     * @param checkReq
     * @param columnLabelForCheck
     * @param addReq
     * @param statusLabel
     */
    private <T extends Enum<T>> void stringLoading(String checkReq, String columnLabelForCheck,
                               String addReq, String statusLabel, Class<T> myEnum){
        try(Connection connection = dataSource.getConnection()) {
            Set<String> missingRefusalTypes =
                    getMissingStrings(checkReq, columnLabelForCheck, connection, myEnum);
            if (!missingRefusalTypes.isEmpty()) {
                connection.setAutoCommit(false);
                try {
                    PreparedStatement statement = connection.prepareStatement(addReq);
                    for (String type : missingRefusalTypes) {
                        statement.setString(1, type);
                        statement.executeUpdate();
                    }
                    connection.commit();
                    logger.info(statusLabel +" a été bien enregistrés");
                } catch (SQLException e) {
                    connection.rollback();
                    logger.error("Une erreur s'est produite lors de " + statusLabel);
                }
            } else {
                logger.info("Aucune nouvelle " + statusLabel +" n'était pas ajouté");
            }
        } catch (SQLException e) {
            logger.error("une erreur s'est produite lors de la consultation du lexique en base de données", e);
        }
    }

    /**
     * Checks if new evaluation types need to be added to DB
     * @param connection
     * @return
     */
    private <T extends Enum<T>> Set<String> getMissingStrings(String reqStatement, String columnLabel,
                                          Connection connection , Class<T> enumClass) throws SQLException{
        Set<String> dbTypes = new HashSet<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(reqStatement);
        while (resultSet.next()) {
            dbTypes.add(resultSet.getString(columnLabel));
        }
        Set<String> enumTypes = new HashSet<>();
        for (T type : enumClass.getEnumConstants()) {
            enumTypes.add(type.name());
        }
        Set<String> missingReasons = new HashSet<>(enumTypes);
        missingReasons.removeAll(dbTypes);
        return missingReasons;
    }


    /**
     * Loads transaction evaluation types to DB
     */
    @Override
    public void loadEvaluationTypeIntoDatabase() {
        stringLoading(REQ_GET_ALL_EVAL_TYPES, "evaluation_label",
                REQ_INSERT_EVAL_TYPE, "Types d'evaluation", EvaluationType.class);
    }

    /**
     * Loads payment refusal types to DB
     */
    @Override
    public void loadPaymentRefusalTypeIntoDatabase() {
        stringLoading(REQ_GET_ALL_REFUSAL_TYPES, "refuse_payment_label",
                REQ_INSERT_REFUSAL_TYPE, "Raisons de refus de paiement", PaymentRefusalType.class);
    }

    /**
     * Load payment methods to DB
     */
    @Override
    public void loadPricingTypesIntoDatabase() {
        stringLoading(REQ_GET_ALL_PRICING_TYPES, "type_pricing",
                REQ_INSERT_PRICING_TYPE, "types de tarification", PricingType.class);
    }

    /** Load reservation closing types into db
     */
    @Override
    public void loadReservationCancelTypesIntoDatabase() {
        stringLoading(REQ_GET_ALL_RESERVATION_CANCEL_TYPES, "cancellation_label",
                REQ_INSERT_RESERVATION_CANCEL_TYPE, "types de cancellation de reservation ", ReservationCancelType.class);
    }

    @Override
    public void loadStationClosingReasonsIntoDatabase() {
        stringLoading(REQ_GET_ALL_STATION_CLOSING_TYPES, "station_closing_type",
                REQ_INSERT_STATION_CLOSING_TYPE, "types de fermeture d'une borne ", StationClosingType.class);
    }

    @Override
    public void loadStationUnavailabilityTypesIntoDatabase() {
        stringLoading(REQ_GET_ALL_STATION_UNAVAILABILITY_TYPES, "unavailability_type",
                REQ_INSERT_STATION_UNAVAILABILITY_TYPE, "raisons d'indisponibilite ", UnavailabilityType.class);
    }

    @Override
    public void loadWeekdaysIntoDatabase() {
        stringLoading(REQ_GET_WEEKDAYS, "day",
                REQ_INSERT_WEEKDAYS, "jours de semaine ", WeekDay.class);
    }


    /**
     * Loads account closing reasons to Database
     */
    @Override
    public void loadAccountClosingReasonsIntoDatabase() {
        stringLoading(REQ_GET_ALL_ACC_CLOSE_REASONS, "label_closing_account_user",
                REQ_INSERT_ACC_CLOSE_REASONS, "Raisons pour fermer la compte", AccountCloseType.class);
    }

    /**
     * Loads plug types to DB
     */
    @Override
    public void loadPlugsIntoDatabase() {
        stringLoading(REQ_GET_ALL_PLUG_TYPES, "plug_type",
                REQ_INSERT_PLUG_TYPE, "Valeurs de plug-type", PlugType.class);
    }

    /**
     * Load Car Brands into DB
     */
    @Override
    public void loadCarBrandsIntoDatabase() {
        stringLoading(REQ_GET_ALL_CAR_BRANDS, "brand_label",
                REQ_INSERT_CAR_BRANDS, "Marques", CarBrand.class);
    }

    /**
     * Load car withdrawal reasons to DB
     */
    @Override
    public void loadCarWithdrawalReasons() {
        stringLoading(REQ_GET_CAR_WITHDRAWAL_REASONS, "car_withdrawal_label",
                REQ_INSERT_CAR_WITHDRAW_REASONS, "Raisons pour retirer la vehicule", CarWithdrawalType.class);
    }

    /**
     * Loads car models into the Database
     */
    @Override
    public void loadCarModelsIntoDatabase() {
        try (Connection connection = dataSource.getConnection()) {
            Set<Car> existingCarModels = getExistingCarModels(connection);
            connection.setAutoCommit(false);
            boolean added = false;
            try {
                for (CarModel model : CarModel.values()) {
                    Car carToCheck = new Car(model.getCarBrand().toString(), model.getLabel(),
                            model.getPlugType().toString());
                    if (!existingCarModels.contains(carToCheck)) {
                        added = true;
                        PreparedStatement insertStatement = connection.prepareStatement(REQ_INSERT_CAR_MODELS);
                        Long plugId = getPlugId(model.getPlugType(), connection);
                        Long brandId = getBrandId(model.getCarBrand(), connection);
                        insertStatement.setLong(1, plugId);
                        insertStatement.setLong(2, brandId);
                        insertStatement.setString(3, model.getLabel());
                        insertStatement.executeUpdate();
                        if (model.getSecondaryPlugType() != null) {
                            carToCheck = new Car(model.getCarBrand().toString(), model.getLabel(),
                                    model.getSecondaryPlugType().toString());
                            if (!existingCarModels.contains(carToCheck)) {
                                plugId = getPlugId(model.getSecondaryPlugType(), connection);
                                insertStatement.setLong(1, plugId);
                                insertStatement.executeUpdate();
                            }}
                    }}
                connection.commit();
                if (added){logger.info("Les Nouveles Modeles a été bien enregistrés");}
                else {logger.info("Aucun modele n'été pas enregistrés");}
            } catch (SQLException e) {
                connection.rollback();
                logger.error("Une erreur s'est produite lors de ajout de modeles de voitures");}
        } catch (SQLException e) {
            logger.error("une erreur s'est produite lors de la consultation du lexique en b" +
                    "ase de données", e);}
    }




    /** Get Car models recorded in Database
     * @param connection
     * @return
     */
    private Set<Car> getExistingCarModels(Connection connection) throws SQLException {
        Set<Car> existingCars = new HashSet<>();
        Statement selectStatement = connection.createStatement();
        ResultSet resultSet = selectStatement.executeQuery(REQ_SELECT_ALL_CARS_MODELS);
        while (resultSet.next()) {
            String brand = resultSet.getString("brand_label");
            String carModel = resultSet.getString("car_model_label");
            String plug = resultSet.getString("plug_type");
            existingCars.add(new Car(brand, carModel, plug));
        }
        return existingCars;
    }

    /**
     * Get brand id from brand
     * @param carBrand
     * @param connection
     * @return
     */
    private Long getBrandId(CarBrand carBrand, Connection connection) throws SQLException{
        Long brandId;
        PreparedStatement statement = connection.prepareStatement(REQ_GET_BRAND_ID);
        statement.setString(1, carBrand.name());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            brandId = resultSet.getLong("id_brand");
        } else {
            brandId = getPlugId(PlugType.AUTRE, connection);
        }
        return brandId;
    }

    /**
     * Get plug ID from plug name
     * @param plug
     * @param connection
     * @return
     */
    private Long getPlugId(PlugType plug, Connection connection) throws SQLException {
        Long plugId;
        PreparedStatement statement = connection.prepareStatement(REQ_GET_PLUG_ID);
        statement.setString(1, plug.name());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            plugId = resultSet.getLong("id_plug_type");
        } else {
            plugId = getPlugId(PlugType.AUTRE, connection);
        }
        return plugId;
    }

}
