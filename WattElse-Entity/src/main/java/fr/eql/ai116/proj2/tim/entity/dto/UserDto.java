package fr.eql.ai116.proj2.tim.entity.dto;

import java.io.Serializable;

public class UserDto implements Serializable {

    private Long userId;
    private String email;
    private String token;

    public UserDto(){}

    public UserDto(Long userId, String email, String token) {
        this.userId = userId;
        this.email = email;
        this.token = token;
    }

    ///  Setters ///
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /// Getters (used to Serialize) ///
    public Long getUserId() {
        return userId;
    }
    public String getEmail() {return email;}
    public String getToken() {return token;}
}
