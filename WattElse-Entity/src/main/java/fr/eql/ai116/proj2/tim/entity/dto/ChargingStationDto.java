package fr.eql.ai116.proj2.tim.entity.dto;

import java.time.LocalDate;

public class ChargingStationDto {

    private long idStationClosingType;
    private long idPlugType;
    private long idCity;
    private long idUser;
    private int powerChargingStation;
    private String addressChargingStation;
    private long longitude;
    private long latitude;
    private String emergencyPhone;

    //Constructeur vide ///


    public ChargingStationDto() {
    }

    //Getters////


    public long getIdStationClosingType() {
        return idStationClosingType;
    }

    public long getIdPlugType() {
        return idPlugType;
    }

    public long getIdCity() {
        return idCity;
    }

    public long getIdUser() {
        return idUser;
    }

    public int getPowerChargingStation() {
        return powerChargingStation;
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

    //Setters///


    public void setIdStationClosingType(long idStationClosingType) {
        this.idStationClosingType = idStationClosingType;
    }

    public void setIdPlugType(long idPlugType) {
        this.idPlugType = idPlugType;
    }

    public void setIdCity(long idCity) {
        this.idCity = idCity;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public void setPowerChargingStation(int powerChargingStation) {
        this.powerChargingStation = powerChargingStation;
    }

    public void setAddressChargingStation(String addressChargingStation) {
        this.addressChargingStation = addressChargingStation;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }
}
