package fr.eql.ai116.proj2.tim.entity.dto;

import java.io.Serializable;

public class ChargingStationDto implements Serializable {

    private Long idOwner;
    private Long idStation;
    private String plug;
    private String city;
    private int powerChargingStation;
    private String addressChargingStation;
    private Float longitude;
    private Float latitude;
    private String emergencyPhone;

    //Constructeurs///


    public ChargingStationDto() {
    }

    //Getters////

    public Long getIdOwner() {
        return idOwner;
    }

    public String getPlug() {
        return plug;
    }

    public String getCity() {
        return city;
    }

    public int getPowerChargingStation() {
        return powerChargingStation;
    }

    public String getAddressChargingStation() {
        return addressChargingStation;
    }

    public Float getLongitude() {
        return longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public long getIdStation() {
        return idStation;
    }


    //Setters///


    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public void setIdStation(long idStation) {
        this.idStation = idStation;
    }

    public void setPlug(String plug) {
        this.plug = plug;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPowerChargingStation(int powerChargingStation) {
        this.powerChargingStation = powerChargingStation;
    }

    public void setAddressChargingStation(String addressChargingStation) {
        this.addressChargingStation = addressChargingStation;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }
}
