package fr.eql.ai116.proj2.tim.dao;

import fr.eql.ai116.proj2.tim.entity.Car;

import java.util.List;

//Vehicule
public interface CarDao {
    void addCar(Car car, long userId);
    void removeCar(Car car);
    void modifyCar(Car car);
    List<Car> getCars(long userId);
}
