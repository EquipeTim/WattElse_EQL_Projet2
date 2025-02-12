package fr.eql.ai116.proj2.tim.business.impl;

import fr.eql.ai116.proj2.tim.business.RegistrationBusiness;
import fr.eql.ai116.proj2.tim.dao.UserDao;
import fr.eql.ai116.proj2.tim.entity.Role;
import fr.eql.ai116.proj2.tim.entity.User;
import fr.eql.ai116.proj2.tim.entity.dto.FullUserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote(RegistrationBusiness.class)
@Stateless
public class RegistrationBusinessImpl implements RegistrationBusiness {
    private static final Logger logger = LogManager.getLogger();

    @EJB
    private UserDao userDao;

    @Override
    public void registerUser(FullUserDto fullUserDto) {

        User user = new User(null, fullUserDto.getName(), fullUserDto.getSurname(),
                fullUserDto.getBirthdate(), fullUserDto.getEmail(), fullUserDto.getAddress(),
                fullUserDto.getCity(), fullUserDto.getPostCode(), fullUserDto.getPhone(),
                fullUserDto.getPassword(), Role.valueOf("USER"));
        logger.error("After User creation");
        logger.error(user.getName());
        userDao.registerUser(user);

    }
}
