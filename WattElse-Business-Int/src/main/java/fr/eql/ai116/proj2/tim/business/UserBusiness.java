package fr.eql.ai116.proj2.tim.business;

import fr.eql.ai116.proj2.tim.entity.User;
import fr.eql.ai116.proj2.tim.entity.dto.FullUserDto;
import fr.eql.ai116.proj2.tim.entity.dto.UserCloseDto;
import jdk.nashorn.internal.parser.Token;

public interface UserBusiness {
    boolean registerUser(FullUserDto fullUserDto);
    boolean closeUserAccount(UserCloseDto userCloseDto);
}
