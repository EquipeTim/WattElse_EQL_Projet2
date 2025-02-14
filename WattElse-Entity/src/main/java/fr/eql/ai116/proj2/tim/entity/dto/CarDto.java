package fr.eql.ai116.proj2.tim.entity.dto;

import java.io.Serializable;

public class CarDto implements Serializable {

    private Long idModelCar;
    private Long userId;
    private String licensePlateNumber;
    private long maxElectricPower;

    public long getMaxElectricPower() {
        return maxElectricPower;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public long getUserId() {
        return userId;
    }

    public long getIdModelCar() {
        return idModelCar;
    }

    //Setter///


    public void setMaxElectricPower(long maxElectricPower) {
        this.maxElectricPower = maxElectricPower;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setIdModelCar(Long idModelCar) {
        this.idModelCar = idModelCar;
    }

}
