package fr.eql.ai116.proj2.tim.entity;

import java.io.Serializable;
import java.time.LocalDate;

// Borne
public class ChargingStation implements Serializable {

    private long idChargingStation;
    private final long PowerChargingStation;
    private final LocalDate registrationStationDate;
    private final LocalDate closingStationDate;
    private final String adresseChargingStation;
    private final long longitude;
    private final long latitude;
    private final String emergencyPhone;

    public ChargingStation(long powerChargingStation, LocalDate registrationStationDate, LocalDate closingStationDate, String adresseChargingStation, long longitude, long latitude, String emergencyPhone, long idChargingStation) {
        PowerChargingStation = powerChargingStation;
        this.registrationStationDate = registrationStationDate;
        this.closingStationDate = closingStationDate;
        this.adresseChargingStation = adresseChargingStation;
        this.longitude = longitude;
        this.latitude = latitude;
        this.emergencyPhone = emergencyPhone;
        this.idChargingStation = idChargingStation;
    }
    /// Getters ///

    public long getIdChargingStation() {
        return idChargingStation;
    }

    public long getPowerChargingStation() {
        return PowerChargingStation;
    }

    public LocalDate getRegistrationStationDate() {
        return registrationStationDate;
    }

    public LocalDate getClosingStationDate() {
        return closingStationDate;
    }

    public String getAdresseChargingStation() {
        return adresseChargingStation;
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

    /// Setters////

}
