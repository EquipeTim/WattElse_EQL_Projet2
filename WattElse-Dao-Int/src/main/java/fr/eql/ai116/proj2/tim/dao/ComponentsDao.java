package fr.eql.ai116.proj2.tim.dao;
import fr.eql.ai116.proj2.tim.entity.Car;
import fr.eql.ai116.proj2.tim.entity.Plug;
import fr.eql.ai116.proj2.tim.entity.PlugType;


import java.util.List;


public interface ComponentsDao {
    //Prise ///
    List<Plug> getAllPlug();
    List<Plug> findByModel(Car car);
    List<String> getCarBrands();
    List<String> getCarModels(String brand);

}
