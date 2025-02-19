package fr.eql.ai116.proj2.tim.dao;

import fr.eql.ai116.proj2.tim.entity.Car;
import fr.eql.ai116.proj2.tim.entity.PlugType;

import java.util.List;

//Vehicule
public interface CarDao {

    boolean addCar(Car car, long userId);
    boolean exists(Car car);
    void removeCar(Car car);
    boolean modifyCar(Car car);
    List<Car> findByUser(String token);
}
