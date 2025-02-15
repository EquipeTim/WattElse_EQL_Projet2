package fr.eql.ai116.proj2.tim.dao;

import fr.eql.ai116.proj2.tim.entity.ChargingStation;
import fr.eql.ai116.proj2.tim.entity.PlugType;

import java.util.List;

//Borne
public interface ChargingStationDao {
    List<ChargingStation> findChargingStation(Float centerLat, Float centerLong, Integer radius, PlugType plug);
    void addChargingStation(ChargingStation chargingStation, long userId);
    void removeChargingStation(ChargingStation chargingStation);
    void modifyChargingStation(ChargingStation chargingStation);
    List<ChargingStation> getChargingStations(long userId);
}
