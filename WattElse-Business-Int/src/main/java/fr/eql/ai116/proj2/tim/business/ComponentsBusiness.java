package fr.eql.ai116.proj2.tim.business;

import fr.eql.ai116.proj2.tim.entity.Plug;
import fr.eql.ai116.proj2.tim.entity.PlugType;
import fr.eql.ai116.proj2.tim.entity.dto.CarDto;

import java.util.List;

public interface ComponentsBusiness {
    List<Plug> findPlug(CarDto carDto);
    List<Plug> getAllPlug();
    void loadPlugsIntoDatabase();
    List<String> getAccountCloseReasons();
}
