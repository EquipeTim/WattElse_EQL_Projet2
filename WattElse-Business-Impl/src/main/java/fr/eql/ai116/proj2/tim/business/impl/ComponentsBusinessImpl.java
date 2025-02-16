package fr.eql.ai116.proj2.tim.business.impl;

import fr.eql.ai116.proj2.tim.business.ComponentsBusiness;
import fr.eql.ai116.proj2.tim.dao.ComponentsDao;
import fr.eql.ai116.proj2.tim.entity.Car;
import fr.eql.ai116.proj2.tim.entity.dto.CarDto;
import fr.eql.ai116.proj2.tim.entity.dto.ChoicesDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Remote(ComponentsBusiness.class)
@Stateless

public class ComponentsBusinessImpl implements ComponentsBusiness {

    @EJB
    private ComponentsDao componentsDao;


    @Override
    public List<ChoicesDto> findPlug(CarDto carDto) {
        Car car = new Car(carDto.getBrand(), carDto.getCarModel(), null);
        return componentsDao.findByModel(car);
    }

    //@Override indique que cette méthode redéfinit (ou "implémente") une méthode définie dans une interface ou une classe parente.
    @Override
    public List<ChoicesDto> getAllPlug() {
        return componentsDao.getAllPlug();
    }


    @Override
    public List<ChoicesDto> getAccountCloseReasons() {
        return componentsDao.getAccountCloseReasons();
    }

    @Override
    public List<ChoicesDto> getCarWithdrawalReasons() {
        return componentsDao.getCarWithdrawalReasons();
    }

    @Override
    public List<ChoicesDto> getEvaluationTypes() {
        return componentsDao.getEvaluationTypes();
    }

    @Override
    public List<ChoicesDto> getPaymentRefusalReasons() {
        return componentsDao.getPaymentRefusalReasons();
    }

    @Override
    public List<ChoicesDto> getCarBrands() {
        return componentsDao.getCarBrands();
    }

    @Override
    public List<ChoicesDto> getCarModels(String brand) {
        return componentsDao.getCarModels(brand);
    }


}




