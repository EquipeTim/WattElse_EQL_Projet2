package fr.eql.ai116.proj2.tim.entity;

import com.sun.prism.shader.AlphaOne_Color_AlphaTest_Loader;

import java.time.LocalDate;

// Vehicule
public class Car {

    private long idCar;
    private final long licensePlateNumber;
    private final LocalDate registrationDateCar;
    private final LocalDate removeDateCar;
    private long maxElectricPower;

    public Car(long idCar, long licensePlateNumber, LocalDate registrationDateCar, LocalDate removeDateCar, long maxElectricPower) {
        this.idCar = idCar;
        this.licensePlateNumber = licensePlateNumber;
        this.registrationDateCar = registrationDateCar;
        this.removeDateCar = removeDateCar;
        this.maxElectricPower = maxElectricPower;
    }

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

    public void setIdCar(long idCar) {
        this.idCar = idCar;
    }
}
