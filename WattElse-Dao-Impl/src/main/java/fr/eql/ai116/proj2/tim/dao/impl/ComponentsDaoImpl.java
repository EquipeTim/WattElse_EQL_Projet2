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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Remote(ComponentsDao.class)
@Stateless

public class ComponentsDaoImpl implements ComponentsDao {

    private static final Logger logger = LogManager.getLogger();

    //Obtenir toutes les prises /////

    private static final String REQ_SELECT_PLUG = "SELECT * FROM plug_type ";

//DataSource object to get a connection to database///

    private static final DataSource dataSource= new WattElseDataSource();


    @Override
    public List<Plug> getAllPlug() {
        // créer une liste vide de nom plug_type
        List<Plug> plug_type = new ArrayList<>();
        logger.error("arrivejusqua ici");
        try(Connection connection = dataSource.getConnection()) {
            // on crée un objet Statement qui permet d'executer les requettes sql sur la bd.
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_SELECT_PLUG);

            while(resultSet.next()){
                //resultSet.getString("plug_type"): obtenir la valeur de la colonne plug_type de type string, cette valeur s'ajoute
                // à la liste
                logger.error(resultSet.getString("plug_type"));
                Plug plug = new Plug(resultSet.getLong("id_plug_type"), PlugType.valueOf(resultSet.getString("plug_type")));
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
}
