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
