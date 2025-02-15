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

@Remote(ChargingStationDao.class)
@Stateless
public class ChargingStationDaoImpl implements ChargingStationDao{
    private static final Logger logger= LogManager.getLogger();
    private final DataSource dataSource = new WattElseDataSource();


    @Override
    public List<ChargingStation> findChargingStation() {
        return null;
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
