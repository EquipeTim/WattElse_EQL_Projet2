package fr.eql.ai116.proj2.tim.entity.dto;

import fr.eql.ai116.proj2.tim.entity.PlugType;
import fr.eql.ai116.proj2.tim.entity.PricingType;

import java.io.Serializable;

public class ChargingStationDto implements Serializable {

    private Long idOwner;
    private Long idStation;
    private String city;
    private int powerChargingStation;
    private String addressChargingStation;
    private Float longitude;
    private Float latitude;
    private String emergencyPhone;
    private String plugType;
    private String pricingType;
    private Float price;

    //Constructeurs///


    public ChargingStationDto() {
    }

    public ChargingStationDto(Long idStation, String city, int powerChargingStation,
                              String addressChargingStation, Float longitude, Float latitude,
                              String emergencyPhone, String plugType, String pricingType,
                              Float price) {
        this.idStation = idStation;
        this.city = city;
        this.powerChargingStation = powerChargingStation;
        this.addressChargingStation = addressChargingStation;
        this.longitude = longitude;
        this.latitude = latitude;
        this.emergencyPhone = emergencyPhone;
        this.plugType = plugType;
        this.pricingType = pricingType;
        this.price = price;

    }
    //Getters////

    public Long getIdOwner() {
        return idOwner;
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

    public String getPlugType() {
        return plugType;
    }

    public String getPricingType() {
        return pricingType;
    }

    public Float getPrice() {
        return price;
    }

    //Setters///


    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public void setIdStation(long idStation) {
        this.idStation = idStation;
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
