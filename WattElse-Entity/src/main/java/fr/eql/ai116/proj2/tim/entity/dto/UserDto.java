package fr.eql.ai116.proj2.tim.entity.dto;

import java.io.Serializable;

public class UserDto implements Serializable {

    private final long id;
    private final String name;
    private final String surname;
    private final String token;

    public UserDto(long id, String name, String surname, String token) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.token = token;
    }

    /// Getters (pour sérialiser) ///
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getToken() {return token;}
}
