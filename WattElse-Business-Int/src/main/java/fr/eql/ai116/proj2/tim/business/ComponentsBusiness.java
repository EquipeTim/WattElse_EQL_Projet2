package fr.eql.ai116.proj2.tim.business;

import fr.eql.ai116.proj2.tim.entity.dto.CarDto;
import fr.eql.ai116.proj2.tim.entity.dto.ChoicesDto;

import java.util.List;

public interface ComponentsBusiness {
    List<ChoicesDto> findPlug(CarDto carDto);
    List<ChoicesDto> getAllPlug();
    List<ChoicesDto> getCarBrands();
    List<ChoicesDto> getCarModels(String brand);
    List<ChoicesDto> getAccountCloseReasons();
    List<ChoicesDto> getCarWithdrawalReasons();
    List<ChoicesDto> getEvaluationTypes();
    List<ChoicesDto> getPaymentRefusalReasons();
}
