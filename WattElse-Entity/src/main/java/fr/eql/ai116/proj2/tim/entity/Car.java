package fr.eql.ai116.proj2.tim.entity;

import java.io.Serializable;

// Vehicule
public class Car implements Serializable {

    private Long idCar;
    private String licensePlate;
    private Long maxElectricPower;
    private String brand;
    private String carModel;

    public Car(Long idCar, String carModel, String brand,
               Long maxElectricPower, String licensePlate) {
        this.idCar = idCar;
        this.carModel = carModel;
        this.brand = brand;
        this.maxElectricPower = maxElectricPower;
        this.licensePlate = licensePlate;
    }


    ///getters///

    public Long getIdCar() {
        return idCar;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Long getMaxElectricPower() {
        return maxElectricPower;
    }


    public String getBrand() {
        return brand;
    }

    public String getCarModel() {
        return carModel;
    }

    ///Setter//

    public void setIdCar(long idCar) {
        this.idCar = idCar;
    }

    public void setMaxElectricPower(long maxElectricPower) {
        this.maxElectricPower = maxElectricPower;
    }

}
