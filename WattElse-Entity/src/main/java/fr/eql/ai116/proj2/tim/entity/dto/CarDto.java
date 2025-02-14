package fr.eql.ai116.proj2.tim.entity.dto;

import fr.eql.ai116.proj2.tim.entity.CarBrand;
import fr.eql.ai116.proj2.tim.entity.CarModel;

import java.io.Serializable;

public class CarDto implements Serializable {

    private Long idModelCar;
    private Long userId;
    private String licensePlateNumber;
    private Long maxElectricPower;
    private String brand;
    private String carModel;

    public Long getMaxElectricPower() {
        return maxElectricPower;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getIdModelCar() {
        return idModelCar;
    }

    public String getBrand() {
        return brand;
    }

    public String getCarModel() {
        return carModel;
    }

    //Setter///


    public void setMaxElectricPower(Long maxElectricPower) {
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

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
}
