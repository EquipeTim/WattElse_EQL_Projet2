package fr.eql.ai116.proj2.tim.entity;

import java.io.Serializable;
import java.util.Objects;

// Vehicule
public class Car implements Serializable {

    private Long idCar;
    private String licensePlate;
    private Long maxElectricPower;
    private String brand;
    private String carModel;
    private String plug;

    public Car(Long idCar, String carModel, String brand,
               Long maxElectricPower, String licensePlate, String plug) {
        this.idCar = idCar;
        this.carModel = carModel;
        this.brand = brand;
        this.maxElectricPower = maxElectricPower;
        this.licensePlate = licensePlate;
        this.plug = plug;
    }

    public Car(String brand, String carModel, String plug) {
        this.brand = brand;
        this.carModel = carModel;
        this.plug = plug;
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

    public String getPlug() {
        return plug;
    }

    ///Setter//

    public void setIdCar(long idCar) {
        this.idCar = idCar;
    }

    public void setMaxElectricPower(long maxElectricPower) {
        this.maxElectricPower = maxElectricPower;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(brand, car.brand) && Objects.equals(carModel, car.carModel) && Objects.equals(plug, car.plug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, carModel, plug);
    }
}
