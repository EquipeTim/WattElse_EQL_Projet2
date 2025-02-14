package fr.eql.ai116.proj2.tim.business.impl;

import fr.eql.ai116.proj2.tim.business.CarBusiness;
import fr.eql.ai116.proj2.tim.dao.CarDao;
import fr.eql.ai116.proj2.tim.entity.Car;
import fr.eql.ai116.proj2.tim.entity.dto.CarDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.sql.Timestamp;
import java.util.List;

@Remote(CarBusiness.class)
@Stateless
public class CarBusinessImpl implements CarBusiness{
    private static final Logger logger = LogManager.getLogger();

    @EJB
    private CarDao carDao;


    @Override
    public void addCar(CarDto newCarDto) {
      Car car = new Car(newCarDto.getUserId(),newCarDto.getIdModelCar(), newCarDto.getLicensePlateNumber(), Timestamp.valueOf(newCarDto.getRegistrationDateCar()), Timestamp.valueOf(newCarDto.getRemoveDateCar()),newCarDto.getMaxElectricPower());
      carDao.addCar(car, newCarDto.getUserId(), newCarDto.getIdModelCar());

    }

    @Override
    public void removeCar(CarDto car) {

    }

    @Override
    public void modifyCar(CarDto car) {

    }

    @Override
    public List<Car> findUserCar(long userid) {
        return carDao.findByUser(userid);
    }
}



