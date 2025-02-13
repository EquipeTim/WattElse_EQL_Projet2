package fr.eql.ai116.proj2.tim.business.impl;

import fr.eql.ai116.proj2.tim.business.UserBusiness;
import fr.eql.ai116.proj2.tim.dao.UserDao;
import fr.eql.ai116.proj2.tim.entity.Role;
import fr.eql.ai116.proj2.tim.entity.User;
import fr.eql.ai116.proj2.tim.entity.dto.FullUserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote(UserBusiness.class)
@Stateless
public class UserBusinessImpl implements UserBusiness {
    private static final Logger logger = LogManager.getLogger();

    @EJB
    private UserDao userDao;

    @Override
    public boolean registerUser(FullUserDto fullUserDto) {
        User user = new User(null, fullUserDto.getName(), fullUserDto.getSurname(),
                fullUserDto.getBirthdate(), fullUserDto.getEmail(), fullUserDto.getAddress(),
                fullUserDto.getCity(), fullUserDto.getPostCode(), fullUserDto.getPhone(),
                fullUserDto.getPassword(), Role.valueOf("USER"));
        return userDao.registerUser(user);
    }
}
