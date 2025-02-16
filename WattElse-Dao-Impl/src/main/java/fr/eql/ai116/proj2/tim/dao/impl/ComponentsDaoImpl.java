package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.ComponentsDao;
import fr.eql.ai116.proj2.tim.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.proj2.tim.entity.Car;
import fr.eql.ai116.proj2.tim.entity.CarBrand;
import fr.eql.ai116.proj2.tim.entity.Plug;
import fr.eql.ai116.proj2.tim.entity.PlugType;
import fr.eql.ai116.proj2.tim.entity.dto.CarDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.security.cert.CertificateRevokedException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Remote(ComponentsDao.class)
@Stateless

public class ComponentsDaoImpl implements ComponentsDao {
    private static final DataSource dataSource= new WattElseDataSource();
    private static final Logger logger = LogManager.getLogger();

    private static final String REQ_SELECT_PLUG = "SELECT * FROM plug_type ";

    private static final String REQ_GET_CAR_PLUGS = "SELECT * FROM model_car mc JOIN brand_CAR bc " +
            "ON mc.id_brand = bc.id_brand JOIN plug_type pt ON mc.id_plug_type = pt.id_plug_type " +
            "WHERE bc.brand_label = ? AND mc.car_model_label = ?";
    private static final String REQ_GET_CAR_BRANDS = "SELECT * FROM brand_CAR";
    private static final String REQ_GET_MODELS = "SELECT DISTINCT * FROM model_car mc JOIN brand_CAR bc " +
            "ON mc.id_brand = bc.id_brand WHERE bc.brand_label = ?";

    /**
     * Returns list of all plugs registered in DB
     * @return
     */
    @Override
    public List<Plug> getAllPlug() {
        List<Plug> plug_type = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_SELECT_PLUG);
            while(resultSet.next()){
                Plug plug = new Plug(resultSet.getLong("id_plug_type"),
                        PlugType.valueOf(resultSet.getString("plug_type")));
                plug_type.add(plug);
            }
        } catch (SQLException e) {
            logger.error("une erreur s'est produite lors de la consultation du lexique en base de données", e);
        }
        return plug_type;
    }

    /**
     * Get plugs for this type of car
     * @param car
     * @return
     */
    @Override
    public List<Plug> findByModel(Car car) {
        List<Plug> carPlugs = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_GET_CAR_PLUGS);
            statement.setString(1, car.getBrand());
            statement.setString(2, car.getCarModel());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                carPlugs.add(new Plug(resultSet.getLong("id_plug_type"),
                        PlugType.valueOf(resultSet.getString("plug_type"))));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carPlugs;
    }

    /**
     * Return list of all Car brands registered in DB
     */
    @Override
    public List<String> getCarBrands() {
        List<String> brands = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_GET_CAR_BRANDS);
            while(resultSet.next()){
                brands.add(CarBrand.valueOf(resultSet.getString("brand_label")).name());
            }
        } catch (SQLException e) {
            logger.error("une erreur s'est produite lors de la consultation du lexique en base de données", e);
        }
        return brands;
    }

    /**
     * GEt registered models in the DB associated to the brand
     * @param brand
     * @return
     */
    @Override
    public List<String> getCarModels(String brand) {
        List<String> models = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_GET_MODELS);
            statement.setString(1, brand);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                models.add(resultSet.getString("car_model_label"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return models;
    }



}
