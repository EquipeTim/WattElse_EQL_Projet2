package fr.eql.ai116.proj2.tim.dao;

import fr.eql.ai116.proj2.tim.entity.Car;

import java.util.List;

//Vehicule
public interface CarDao {
    void addCar(Car car, long userId, long idModelCar);
    boolean exists(Car car);
    void removeCar(Car car);
    void modifyCar(Car car);
    int countByUser(long userId);
    List<Car> findByUser(long userId);


}
