package fr.eql.ai116.proj2.tim.business.impl;

import fr.eql.ai116.proj2.tim.business.CarBusiness;
import fr.eql.ai116.proj2.tim.dao.CarDao;
import fr.eql.ai116.proj2.tim.entity.Car;
import fr.eql.ai116.proj2.tim.entity.CarBrand;
import fr.eql.ai116.proj2.tim.entity.CarModel;
import fr.eql.ai116.proj2.tim.entity.PlugType;
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
    public boolean addCar(CarDto newCarDto) {
      Car car = new Car(null, newCarDto.getCarModel(),
              newCarDto.getBrand(), newCarDto.getMaxElectricPower(),
              newCarDto.getLicensePlateNumber(), newCarDto.getPlug());
      return carDao.addCar(car, newCarDto.getUserId());
    }

    @Override
    public void removeCar(CarDto car) {

    }

    @Override
    public void modifyCar(CarDto car) {

    }

    @Override
    public List<Car> findUserCar(String token) {
        return carDao.findByUser(token);
    }

    @Override
    public void loadCarBrandsIntoDatabase() {
        carDao.loadCarBrandsIntoDatabase();
    }

    @Override
    public void loadCarModelsIntoDatabase() {
        carDao.loadCarModelsIntoDatabase();
    }
}



