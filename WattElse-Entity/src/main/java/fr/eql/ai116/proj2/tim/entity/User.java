package fr.eql.ai116.proj2.tim.entity;

import java.io.Serializable;
import java.time.LocalDate;

// Utilisateur
public class User implements Serializable {
    private Long userId;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String city;
    private String postCode;
    private Role role;

    public User(Long userId, String name, String surname,
                LocalDate birthdate, String email,
                String address, String city,
                String postCode, String phone,
                String password, Role role) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.email = email;
        this.address = address;
        this.city = city;
        this.postCode = postCode;
        this.phone = phone;
        this.password = password;
        this.role = role;

    }
    /// SETTER ///
    public void setUserId(long userId) {this.userId = userId;}
    public void setPassword(String password) {this.password = password;}

    ///  GETTERS ///
    public long getId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCity() {return city;}

    public String getPostCode() {return postCode;}

    public LocalDate getBirthDate() {return birthdate;}

    public String getPhoneNumber() {return phone;}

    public String getEmail() { return email;}

    public String getPassword() {return password;}

    public String getAddress() { return address;}
}
