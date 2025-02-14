package fr.eql.ai116.proj2.tim.business.impl;

import fr.eql.ai116.proj2.tim.business.ComponentsBusiness;
import fr.eql.ai116.proj2.tim.dao.ComponentsDao;
import fr.eql.ai116.proj2.tim.entity.Plug;
import fr.eql.ai116.proj2.tim.entity.dto.CarDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Remote(ComponentsBusiness.class)
@Stateless

public class ComponentsBusinessImpl implements ComponentsBusiness {


    private static final Logger logger = LogManager.getLogger();

    private static final int CONNECT_TIMEOUT = 15000;
    private static final int READ_TIMEOUT = 60000;

    @EJB
    private ComponentsDao componentsDao;


    @Override
    public List<String> findPlug(CarDto carDto) {
        {
            return componentsDao.findByModel(carDto);
        }
    }


    //@Override indique que cette méthode redéfinit (ou "implémente") une méthode définie dans une interface ou une classe parente.
    @Override
    public List<Plug> getAllPlug() {
        return componentsDao.getAllPlug();
    }

}




