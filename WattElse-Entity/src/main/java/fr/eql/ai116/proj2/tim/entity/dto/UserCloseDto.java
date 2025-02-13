package fr.eql.ai116.proj2.tim.entity.dto;

import java.io.Serializable;

public class UserCloseDto implements Serializable {
    private Long userId;
    private Long reasonId;
    private String token;

    /// Constructeur vide (par défaut ici) nécessaire pour la réification ///

    ///  Getters ///
    public Long getUserId() {
        return userId;
    }
    public String getToken() {
        return token;
    }
    public Long getReasonId() {return reasonId;}

    /// Setters (pour réifier) ///
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public void setReasonId(Long reasonId) {this.reasonId = reasonId;}
    public void setToken(String token) {
        this.token = token;
    }
}
