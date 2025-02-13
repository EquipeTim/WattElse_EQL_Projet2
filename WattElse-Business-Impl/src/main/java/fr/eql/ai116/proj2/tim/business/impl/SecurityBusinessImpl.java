package fr.eql.ai116.proj2.tim.business.impl;

import fr.eql.ai116.proj2.tim.business.AuthenticationException;
import fr.eql.ai116.proj2.tim.business.AuthorizationException;
import fr.eql.ai116.proj2.tim.business.SecurityBusiness;
import fr.eql.ai116.proj2.tim.entity.Role;
import fr.eql.ai116.proj2.tim.entity.Session;
import fr.eql.ai116.proj2.tim.entity.User;
import fr.eql.ai116.proj2.tim.dao.UserDao;
import fr.eql.ai116.proj2.tim.entity.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.sql.Timestamp;
import java.time.Instant;


@Remote(SecurityBusiness.class)
@Stateless
public class SecurityBusinessImpl implements SecurityBusiness {
    private static final int SESSION_TIME = 30 * 60 * 1000;
    private static final Logger logger = LogManager.getLogger();

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
        return new UserDto(user.getId(), user.getEmail(),token);
    }

    @Override
    public void authorize(String authorization, Role role) throws AuthorizationException {
        String token = authorization.substring(7);
        Session session = userDao.findSession(token);
        if (session == null) {
            throw new AuthorizationException("Pas de session correspondant au token fourni.");
        }
        if (Timestamp.from(Instant.now()).getTime() - session.getTimestamp().getTime() > SESSION_TIME) {
            throw new AuthorizationException("Session expirée.");
        }
        Role ownerRole = userDao.findRoleByIdUser(session.getUserId());
        checkRole(role, ownerRole);
    }

    private void checkRole(Role authorizedRole, Role ownerRole) throws AuthorizationException {
        switch (ownerRole) {
            case USER:
                if (authorizedRole.equals(Role.ADMIN)) {
                    throw new AuthorizationException("Rôle insuffisant.");
                }
            case ADMIN:
                break;
            default:
                if (authorizedRole.equals(Role.USER) || authorizedRole.equals(Role.ADMIN)) {
                    throw new AuthorizationException("Rôle insuffisant.");
                }
        }
    }

    private String issueToken(String login) { return String.valueOf(("Tequila13_" + login).hashCode());}
}
