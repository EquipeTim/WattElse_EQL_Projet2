package fr.eql.ai116.proj2.tim.entity.dto;

import java.time.LocalDate;

public class CarDto {

    private long idModelCar;
    private long userId;
    private long licensePlateNumber;
    private LocalDate registrationDateCar;
    private LocalDate removeDateCar;
    private long maxElectricPower;

    //Constructor vide par defaut ////

    public CarDto() {
    }
   /* réification désigne le processus par lequel un objet abstrait, comme une fonction,
    une structure de données ou un type générique, est transformé en une forme qui peut être
     manipulée explicitement par le programme. Cela permet de "manipuler des abstractions"
     comme des objets réels dans le code.*/


    /// Getters ///
    public long getModelId() {
        return idModelCar;
    }

    public long getUserId() {
        return userId;
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

    //Setters ///


    public void setModelId(long modelId) {
        this.idModelCar = idModelCar;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setLicensePlateNumber(long licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public void setRegistrationDateCar(LocalDate registrationDateCar) {
        this.registrationDateCar = registrationDateCar;
    }

    public void setRemoveDateCar(LocalDate removeDateCar) {
        this.removeDateCar = removeDateCar;
    }

    public void setMaxElectricPower(long maxElectricPower) {
        this.maxElectricPower = maxElectricPower;
    }
}
