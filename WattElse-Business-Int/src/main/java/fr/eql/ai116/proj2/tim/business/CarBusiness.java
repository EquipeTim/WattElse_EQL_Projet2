package fr.eql.ai116.proj2.tim.business;

import fr.eql.ai116.proj2.tim.entity.Car;
import fr.eql.ai116.proj2.tim.entity.dto.CarDto;

import java.util.List;

public interface CarBusiness {
    boolean addCar(CarDto newCarDto);
    void removeCar(CarDto car);
    boolean modifyCar(CarDto carDto);
    List<Car> findUserCar(String token);



}
