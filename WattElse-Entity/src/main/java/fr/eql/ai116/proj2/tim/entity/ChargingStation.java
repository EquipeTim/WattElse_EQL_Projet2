package fr.eql.ai116.proj2.tim.entity;

import java.io.Serializable;

// Borne
public class ChargingStation implements Serializable {

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

    public ChargingStation(Long idStation, String city, int powerChargingStation,
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

    public Long getIdOwner() {
        return idOwner;
    }

    public Long getIdStation() {
        return idStation;
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

    public String getEmergencyPhone() {
        return emergencyPhone;
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

    public Float getLatitude() {
        return latitude;
    }
}
