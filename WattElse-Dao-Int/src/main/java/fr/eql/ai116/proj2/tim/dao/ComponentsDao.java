package fr.eql.ai116.proj2.tim.dao;

import java.util.List;


public interface ComponentsDao {


    //Prise ///
    List<String> getAllPlug(String plugType);
    List<String> findByModel(long idModelCar);

    ///  motif retraction voiture - CarWithdrawalType /// enum cree mais pas de classe ou classe dto
    List<String> getAllCarWithdrawalType(String carWithdrawalType);


}
