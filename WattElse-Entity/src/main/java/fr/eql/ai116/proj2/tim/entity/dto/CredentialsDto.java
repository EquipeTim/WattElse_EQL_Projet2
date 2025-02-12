package fr.eql.ai116.proj2.tim.entity.dto;

import java.io.Serializable;

public class CredentialsDto implements Serializable {
    private String email;
    private String password;

    /// Constructeur vide (par défaut ici) nécessaire pour la réification /// (deserialization - convert to object)

    /// Getters ///
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    /// Setters (pour réifier) ///
    public void setEmail(String login) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
