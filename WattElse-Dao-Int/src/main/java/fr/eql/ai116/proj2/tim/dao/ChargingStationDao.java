package fr.eql.ai116.proj2.tim.dao.impl;

import java.util.List;

//Borne
public interface ChargingStationDao {
    void addChargingStation(ChargingStation chargingStation, long userId);
    void removeChargingStation(ChargingStation chargingStation);
    void modifyChargingStation(ChargingStation chargingStation);
    List<ChargingStation> getChargingStations(long userId);
}
