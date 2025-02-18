package fr.eql.ai116.proj2.tim.entity.dto;

import java.io.Serializable;

public class UserDto implements Serializable {

    private Long userId;
    private String name;
    private String surname;
    private String email;
    private String token;

    public UserDto(){}

    public UserDto(Long userId, String name, String surname, String email, String token) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.token = token;
    }

    ///  Setters ///
    public void setUserId(Long userId) {this.userId = userId;}

    public void setEmail(String email) {this.email = email;}

    public void setToken(String token) {this.token = token;}

    public void setName(String name) {this.name = name;}

    public void setSurname(String surname) {this.surname = surname;}

    /// Getters (used to Serialize) ///
    public Long getUserId() {return userId;}
    public String getEmail() {return email;}
    public String getToken() {return token;}
    public String getName() {return name;}
    public String getSurname() {return surname;}
}
