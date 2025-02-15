package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.ComponentsDao;
import fr.eql.ai116.proj2.tim.dao.impl.connection.WattElseDataSource;
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
import java.util.List;

@Remote(ComponentsDao.class)
@Stateless

public class ComponentsDaoImpl implements ComponentsDao {
    private static final DataSource dataSource= new WattElseDataSource();
    private static final Logger logger = LogManager.getLogger();

    private static final String REQ_SELECT_PLUG = "SELECT * FROM plug_type ";

    private static final String REQ_EMPTY_PLUG_TYPE = "DELETE FROM plug_type";
    private static final String REQ_RESET_PLUG_TYPE = "ALTER TABLE plug_type AUTO_INCREMENT = 1";
    private static final String REQ_INSERT_PLUG_TYPE = "INSERT INTO plug_type (plug_type) VALUES (?)";


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

    @Override
    public List<String> findByModel(CarDto carDto) {
        return null;
    }

    @Override
    public List<String> getAllCarWithdrawalType(String carWithdrawalType) {
        return null;
    }

    /**
     * Loads plug types to DB
     */
    @Override
    public void loadPlugsIntoDatabase() {
        try(Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(REQ_EMPTY_PLUG_TYPE);
                statement.executeUpdate(REQ_RESET_PLUG_TYPE);
                PreparedStatement insertStatement = connection.prepareStatement(REQ_INSERT_PLUG_TYPE);
                for (PlugType plugType : PlugType.values()) {
                    insertStatement.setString(1, plugType.name());
                    insertStatement.executeUpdate();
                }
                connection.commit();
                logger.info("Les valeurs de plug-type a été bien enregistrés ");
            } catch(SQLException e){
                connection.rollback();
                logger.error("Une erreur s'est produite lors de ajout de valeurs d'enum Plug-Types");
            }
        } catch (SQLException e) {
            logger.error("une erreur s'est produite lors de la consultation du lexique en base de données", e);
        }
    }
}
