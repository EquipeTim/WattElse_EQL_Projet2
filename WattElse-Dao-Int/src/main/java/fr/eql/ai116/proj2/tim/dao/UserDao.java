package fr.eql.ai116.proj2.tim.dao;

import fr.eql.ai116.proj2.tim.entity.Role;
import fr.eql.ai116.proj2.tim.entity.Session;
import fr.eql.ai116.proj2.tim.entity.User;

// Utilisateur

public interface UserDao {
    User authenticate(String email, String password);
    Session findSession(String token);
    void updateSession(String token, long userId);
    Role findRoleByIdUser(long userId);
    boolean registerUser(User user);
    boolean closeUserAccount(Long userId, Long closeReasonId);
    User getUserById(Long userId);
    boolean isAccountOwner(User user, String token);
}
