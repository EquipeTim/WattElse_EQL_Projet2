package fr.eql.ai116.proj2.tim.business;

import fr.eql.ai116.proj2.tim.entity.dto.FullUserDto;
import fr.eql.ai116.proj2.tim.entity.dto.UserCloseDto;

public interface UserBusiness {
    boolean registerUser(FullUserDto fullUserDto);
    boolean closeUserAccount(UserCloseDto userCloseDto);
    boolean updateUser(FullUserDto fullUserDto, String token);
    FullUserDto getUserData(String token);
}
