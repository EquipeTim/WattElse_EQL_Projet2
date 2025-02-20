package fr.eql.ai116.proj2.tim.business;

import fr.eql.ai116.proj2.tim.entity.Car;
import fr.eql.ai116.proj2.tim.entity.dto.CarCloseDto;
import fr.eql.ai116.proj2.tim.entity.dto.CarDto;

import java.util.List;

public interface CarBusiness {
    boolean addCar(CarDto newCarDto);
    boolean removeCar(CarCloseDto carCloseDto);
    boolean modifyCar(CarDto carDto);
    List<Car> findUserCar(String token);



}
