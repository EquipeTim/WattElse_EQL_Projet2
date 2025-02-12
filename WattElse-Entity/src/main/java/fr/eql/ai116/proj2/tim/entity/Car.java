package fr.eql.ai116.proj2.tim.entity;

import com.sun.prism.shader.AlphaOne_Color_AlphaTest_Loader;

import java.io.Serializable;
import java.time.LocalDate;

// Vehicule
public class Car implements Serializable {

    private long idCar;
    private final long licensePlateNumber;
    private final LocalDate registrationDateCar;
    private final LocalDate removeDateCar;
    private final long maxElectricPower;

    public Car(long idCar, long licensePlateNumber, LocalDate registrationDateCar, LocalDate removeDateCar, long maxElectricPower) {
        this.idCar = idCar;
        this.licensePlateNumber = licensePlateNumber;
        this.registrationDateCar = registrationDateCar;
        this.removeDateCar = removeDateCar;
        this.maxElectricPower = maxElectricPower;
    }

    //getters////

    public long getIdCar() {
        return idCar;
    }

    public long getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public LocalDate getRegistrationDateCar() {
        return registrationDateCar;
    }

    public LocalDate getRemoveDateCar() {
        return removeDateCar;
    }

    public long getMaxElectricPower() {
        return maxElectricPower;
    }

    ///Setter//

    public void setIdCar(long idCar) {
        this.idCar = idCar;
    }
}
