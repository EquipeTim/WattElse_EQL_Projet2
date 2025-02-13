package fr.eql.ai116.proj2.tim.entity.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class FullUserDto implements Serializable {
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String email;
    private String password;
    private String address;
    private String phone_number;
    private String city;
    private String postal_code;

    public FullUserDto() {
    }

    public FullUserDto(String name, String surname, LocalDate birthdate,
                       String email, String password, String address,
                       String phone_number, String city, String postal_code) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone_number = phone_number;
        this.city = city;
        this.postal_code = postal_code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone_number;
    }

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postal_code;
    }
}