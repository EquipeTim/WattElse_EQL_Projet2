package fr.eql.ai116.proj2.tim.dao;

import fr.eql.ai116.proj2.tim.entity.ChargingStation;
import fr.eql.ai116.proj2.tim.entity.OpeningHour;
import fr.eql.ai116.proj2.tim.entity.PlugType;
import fr.eql.ai116.proj2.tim.entity.Unavailability;
import fr.eql.ai116.proj2.tim.entity.dto.ChargingStationDto;
import fr.eql.ai116.proj2.tim.entity.dto.ChoicesDto;
import fr.eql.ai116.proj2.tim.entity.dto.SearchDto;

import java.util.List;

//Borne
public interface ChargingStationDao {
    List<ChargingStation> findChargingStation(SearchDto searchDto);
    void addChargingStation(ChargingStation chargingStation, long userId);
    void removeChargingStation(ChargingStation chargingStation);
    void modifyChargingStation(ChargingStation chargingStation);
    ChargingStation getChargingStationById(long stationId);
    List<OpeningHour> getOpeningHours(Long stationId, String timeZone);
    List<OpeningHour> getReservedTimeSlots(Long stationId, String date);
    List<OpeningHour> getSpecificDayOpeningHours(Long stationId, String date);
    List<Unavailability> getUnavailableDays(Long stationId);

}
