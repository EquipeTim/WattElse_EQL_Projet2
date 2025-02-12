package fr.eql.ai116.proj2.tim.business;

import fr.eql.ai116.proj2.tim.entity.Role;
import fr.eql.ai116.proj2.tim.entity.dto.UserDto;

public interface SecurityBusiness {
    UserDto authenticate(String login, String password) throws AuthenticationException;
    void authorize(String authorization, Role role) throws AuthorizationException;
}

