package fr.eql.ai116.proj2.tim.business;

import fr.eql.ai116.proj2.tim.entity.ChargingStation;
import fr.eql.ai116.proj2.tim.entity.dto.ChargingStationDto;
import fr.eql.ai116.proj2.tim.entity.dto.SearchDto;

import java.util.List;

public interface TerminalBusiness {
    List<ChargingStationDto> findTerminals(SearchDto search);
    ChargingStationDto findTerminalsById(Long stationId);
}
