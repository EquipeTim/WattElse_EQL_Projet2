package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.impl.dto.UserDto;

public interface SecurityBusiness {
    UserDto authenticate(String login, String password) throws AuthenticationException;
    void authorize(String authorization) throws AuthorizationException;
}

