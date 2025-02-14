package fr.eql.ai116.proj2.tim.entity;

import java.io.Serializable;

// Vehicule
public class Car implements Serializable {

    private Long idCar;
    private String licensePlate;
    private Long maxElectricPower;
    private Long idModelCar;


    public Car(Long idCar, Long idModelCar, String licensePlate,  Long maxElectricPower) {
        this.idCar = idCar;
        this.maxElectricPower = maxElectricPower;
        this.licensePlate = licensePlate;
        this.idModelCar = idModelCar;
    }

    //getters////

    public long getIdCar() {
        return idCar;
    }

    public long getMaxElectricPower() {
        return maxElectricPower;
    }

    public String getLicensePlate() {return licensePlate;}

    public Long getIdModelCar() {return idModelCar;}

    ///Setter//

    public void setIdCar(long idCar) {
        this.idCar = idCar;
    }

    public void setMaxElectricPower(long maxElectricPower) {
        this.maxElectricPower = maxElectricPower;
    }

}
