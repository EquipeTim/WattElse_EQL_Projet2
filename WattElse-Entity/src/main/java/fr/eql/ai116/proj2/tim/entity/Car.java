package fr.eql.ai116.proj2.tim.entity;

import java.io.Serializable;
import java.sql.Timestamp;


// Vehicule
public class Car implements Serializable {

    private long idCar;

    private  Timestamp registrationDateCar;
    private  Timestamp removeDateCar;
    private  long maxElectricPower;

    public Car() {
    }

    public Car(long userId, long idModelCar, long idCar, Timestamp registrationDateCar, Timestamp removeDateCar, long maxElectricPower) {
        this.idCar = idCar;

        this.registrationDateCar = registrationDateCar;
        this.removeDateCar = removeDateCar;
        this.maxElectricPower = maxElectricPower;
    }

    //getters////

    public long getIdCar() {
        return idCar;
    }


    public Timestamp getRegistrationDateCar() {
        return registrationDateCar;
    }

    public Timestamp getRemoveDateCar() {
        return removeDateCar;
    }

    public long getMaxElectricPower() {
        return maxElectricPower;
    }

    ///Setter//

    public void setIdCar(long idCar) {
        this.idCar = idCar;
    }

    public void setRegistrationDateCar(Timestamp registrationDateCar) {
        this.registrationDateCar = registrationDateCar;
    }

    public void setMaxElectricPower(long maxElectricPower) {
        this.maxElectricPower = maxElectricPower;
    }

    public void setRemoveDateCar(Timestamp removeDateCar) {
        this.removeDateCar = removeDateCar;
    }
}
