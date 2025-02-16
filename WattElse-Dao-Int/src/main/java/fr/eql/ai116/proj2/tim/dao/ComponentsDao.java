package fr.eql.ai116.proj2.tim.dao;
import fr.eql.ai116.proj2.tim.entity.Car;
import fr.eql.ai116.proj2.tim.entity.dto.ChoicesDto;


import java.util.List;


public interface ComponentsDao {
    //Prise ///
    List<ChoicesDto> getAllPlug();
    List<ChoicesDto> findByModel(Car car);
    List<ChoicesDto> getCarBrands();
    List<ChoicesDto> getCarModels(String brand);
    List<ChoicesDto> getAccountCloseReasons();
    List<ChoicesDto> getCarWithdrawalReasons();
    List<ChoicesDto> getEvaluationTypes();
    List<ChoicesDto> getPaymentRefusalReasons();

}
