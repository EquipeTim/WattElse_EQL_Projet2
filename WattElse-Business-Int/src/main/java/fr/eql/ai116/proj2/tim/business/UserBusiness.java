package fr.eql.ai116.proj2.tim.business;

import fr.eql.ai116.proj2.tim.entity.dto.FullUserDto;

public interface UserBusiness {
    boolean registerUser(FullUserDto fullUserDto);
}
