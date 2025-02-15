package fr.eql.ai116.proj2.tim.entity;

import java.io.Serializable;

// Borne
public class ChargingStation implements Serializable {

    private long idChargingStation;
    private long PowerChargingStation;
    private String addressChargingStation;
    private long longitude;
    private long latitude;
    private String emergencyPhone;
    private PlugType plugType;

    public ChargingStation(long idChargingStation, long powerChargingStation,
                           String addressChargingStation, long longitude, long latitude,
                           String emergencyPhone, PlugType plugType) {
        this.idChargingStation = idChargingStation;
        PowerChargingStation = powerChargingStation;
        this.addressChargingStation = addressChargingStation;
        this.longitude = longitude;
        this.latitude = latitude;
        this.emergencyPhone = emergencyPhone;
        this.plugType = plugType;
    }

    ///  Setters
    ///
    public void setIdChargingStation(long idChargingStation) {
        this.idChargingStation = idChargingStation;
    }

    /// Getters

    public long getPowerChargingStation() {
        return PowerChargingStation;
    }

    public String getAddressChargingStation() {
        return addressChargingStation;
    }

    public long getLongitude() {
        return longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public PlugType getPlugType() {
        return plugType;
    }
}
