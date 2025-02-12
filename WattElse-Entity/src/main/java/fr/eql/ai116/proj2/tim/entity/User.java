package fr.eql.ai116.proj2.tim.entity;
// Utilisateur
public class User {
    private long userId;
    private String name;
    private String surName;
    private String email;
    private String password;
    private Role role;

    public User(long userId, String name, String surName, String email, String password, Role role) {
        this.userId = userId;
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    ///  GETTERS ///
    public long getId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surName;
    }

    public String getEmail() {return email;}

    public String getPassword() {return password;}
}
