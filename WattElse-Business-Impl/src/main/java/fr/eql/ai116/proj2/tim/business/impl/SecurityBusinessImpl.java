package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.impl.dto.UserDto;

import javax.ejb.EJB;
import java.sql.Timestamp;
import java.time.Instant;

public class SecurityBusinessImpl implements SecurityBusiness{
    private static final int SESSION_TIME = 30 * 60 * 1000;

    @EJB
    private UserDao userDao;

    @Override
    public UserDto authenticate(String login, String password) throws AuthenticationException {
        User user = userDao.authenticate(login, password);
        if (user == null) {
            throw new AuthenticationException("Identifiant et/ou mot de passe incorrect(s).");
        }
        String token = issueToken(login);
        userDao.updateSession(token, user.getId());
        return new UserDto(user.getId(), user.getName(), user.getSurname(), token);
    }

    @Override
    public void authorize(String authorization) throws AuthorizationException {
        String token = authorization.substring(7);
        Session session = userDao.findSession(token);
        if (session == null) {
            throw new AuthorizationException("Pas de session correspondant au token fourni.");
        }
        if (Timestamp.from(Instant.now()).getTime() - session.getTimestamp().getTime() > SESSION_TIME) {
            throw new AuthorizationException("Session expirée.");
        }
    }

    private String issueToken(String login) {
        return String.valueOf(("Tequila13_" + login).hashCode());
    }
}

