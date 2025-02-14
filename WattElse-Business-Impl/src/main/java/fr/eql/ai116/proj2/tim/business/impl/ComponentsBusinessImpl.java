package fr.eql.ai116.proj2.tim.business.impl;

import fr.eql.ai116.proj2.tim.business.ComponentsBusiness;
import fr.eql.ai116.proj2.tim.dao.ComponentsDao;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Remote(ComponentsBusiness.class)
@Stateless

public class ComponentsBusinessImpl implements ComponentsBusiness{

    @EJB
    ComponentsDao componentsDao;

    @Override
    public List<String> findModelPlug(long idModelCar) {
        return componentsDao.findByModel(idModelCar);
    }

    @Override
    public String fetchExtract(String plug_type) {
        return "";
    }


}
