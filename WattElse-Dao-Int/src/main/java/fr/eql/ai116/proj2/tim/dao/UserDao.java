package fr.eql.ai116.proj2.tim.dao.impl;

// Utilisateur
public interface UserDao {
    User authenticate(String email, String password);
    Session findSession(String token);
    void updateSession(String token, long ownerId);

}
