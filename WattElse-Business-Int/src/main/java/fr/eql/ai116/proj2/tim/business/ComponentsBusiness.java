package fr.eql.ai116.proj2.tim.business;

import java.util.List;

public interface ComponentsBusiness {

    List<String> findModelPlug(long idModelCar);
    String fetchExtract(String plug_type);

}
