package fr.eql.ai116.proj2.tim.dao;

import fr.eql.ai116.proj2.tim.entity.ChargingStation;

import java.util.List;

//Borne
public interface ChargingStationDao {
    List<ChargingStation> findChargingStation();
    void addChargingStation(ChargingStation chargingStation, long userId);
    void removeChargingStation(ChargingStation chargingStation);
    void modifyChargingStation(ChargingStation chargingStation);
    List<ChargingStation> getChargingStations(long userId);
}
