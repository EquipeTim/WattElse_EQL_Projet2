package fr.eql.ai116.proj2.tim.business;

import fr.eql.ai116.proj2.tim.entity.ChargingStation;
import fr.eql.ai116.proj2.tim.entity.OpeningHour;
import fr.eql.ai116.proj2.tim.entity.Revenue;
import fr.eql.ai116.proj2.tim.entity.Unavailability;
import fr.eql.ai116.proj2.tim.entity.dto.ChargingStationDto;
import fr.eql.ai116.proj2.tim.entity.dto.ChoicesDto;
import fr.eql.ai116.proj2.tim.entity.dto.SearchDto;

import java.util.List;

public interface TerminalBusiness {
    List<ChargingStation> findTerminals(SearchDto search);
    ChargingStation findTerminalsById(Long stationId);
    List<OpeningHour> getOpeningHours(SearchDto searchDto);
    List<OpeningHour> getReservedTimeSlots(SearchDto searchDto);
    List<OpeningHour> getAvailableTimeSlots(SearchDto searchDto);
    List<OpeningHour> getSpecificDayOpeningHours(SearchDto searchDto);
    List<Unavailability> getUnavailableDays(Long stationId);
}
