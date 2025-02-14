package fr.eql.ai116.proj2.tim.entity.dto;


import java.io.Serializable;



public class CarDto implements Serializable {

    private long idModelCar;
    private long userId;
    private long licensePlateNumber;


    private String registrationDateCar;
    private String removeDateCar;
    private long maxElectricPower;

    public long getMaxElectricPower() {
        return maxElectricPower;
    }

    public String getRegistrationDateCar() {
        return registrationDateCar;
    }

    public String getRemoveDateCar() {
        return removeDateCar;
    }

    public long getLicensePlateNumber() {
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

    public void setRegistrationDateCar(String registrationDateCar) {
        this.registrationDateCar = registrationDateCar;
    }

    public void setRemoveDateCar(String removeDateCar) {
        this.removeDateCar = removeDateCar;
    }

    public void setLicensePlateNumber(long licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setIdModelCar(long idModelCar) {
        this.idModelCar = idModelCar;
    }


    ///  to string ///
    @Override
    public String toString() {
        return "CarDto{" +
                "idModelCar=" + idModelCar +
                ", userId=" + userId +
                ", licensePlateNumber=" + licensePlateNumber +
                ", registrationDateCar=" + registrationDateCar +
                ", removeDateCar=" + removeDateCar +
                ", maxElectricPower=" + maxElectricPower +
                '}';
    }
}
