package fr.eql.ai116.proj2.tim.business.impl;

import fr.eql.ai116.proj2.tim.business.TerminalBusiness;
import fr.eql.ai116.proj2.tim.dao.ChargingStationDao;
import fr.eql.ai116.proj2.tim.entity.ChargingStation;
import fr.eql.ai116.proj2.tim.entity.dto.ChargingStationDto;
import fr.eql.ai116.proj2.tim.entity.dto.SearchDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Remote(TerminalBusiness.class)
@Stateless
public class TerminalBusinessImpl implements TerminalBusiness {
    private static final Logger logger = LogManager.getLogger();

    @EJB
    private ChargingStationDao chargingStationDao;

    @Override
    public List<ChargingStation> findTerminals(SearchDto searchDto){
        return chargingStationDao.findChargingStation(searchDto.getStartingLat(),
                searchDto.getStartingLong(), searchDto.getSearchRadius(),
                searchDto.getPlugType(), searchDto.getWeekDay());
    }

    @Override
    public ChargingStation findTerminalsById(Long terminalId) {
        return chargingStationDao.getChargingStationById(terminalId);
    }
}
