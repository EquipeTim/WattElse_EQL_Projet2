package fr.eql.ai116.proj2.tim.business.impl;

import fr.eql.ai116.proj2.tim.business.TerminalBusiness;
import fr.eql.ai116.proj2.tim.business.UserBusiness;
import fr.eql.ai116.proj2.tim.entity.ChargingStation;
import fr.eql.ai116.proj2.tim.entity.dto.SearchDto;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Remote(TerminalBusiness.class)
@Stateless
public class TerminalBusinessImpl implements TerminalBusiness {

    @Override
    public List<ChargingStation> searchTerminals(SearchDto search) {
        return null;
    }
}
