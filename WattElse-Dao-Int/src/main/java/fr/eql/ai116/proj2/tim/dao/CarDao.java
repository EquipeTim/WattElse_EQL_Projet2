package fr.eql.ai116.proj2.tim.dao;

import fr.eql.ai116.proj2.tim.entity.Car;
import fr.eql.ai116.proj2.tim.entity.PlugType;

import java.util.List;

//Vehicule
public interface CarDao {

    boolean addCar(Car car, long userId);
    boolean removeCar(Long idCar, Long closeReasonId);
    boolean modifyCar(Car car);
    List<Car> findByUser(String token);
    Car getCarById(Long idCar);
}
