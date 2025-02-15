package fr.eql.ai116.proj2.tim.dao;
import fr.eql.ai116.proj2.tim.entity.Plug;
import fr.eql.ai116.proj2.tim.entity.dto.CarDto;


import java.util.List;


public interface ComponentsDao {

    //Prise ///
    List<Plug> getAllPlug();
    List<String> findByModel(CarDto cardto);

    List<String> getAllCarWithdrawalType(String carWithdrawalType);

    void loadPlugsIntoDatabase();


}
