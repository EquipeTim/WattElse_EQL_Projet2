package fr.eql.ai116.proj2.tim.business.impl;

import fr.eql.ai116.proj2.tim.business.BankCardBusiness;
import fr.eql.ai116.proj2.tim.business.UserBusiness;
import fr.eql.ai116.proj2.tim.dao.BankCardDao;
import fr.eql.ai116.proj2.tim.entity.BankCard;
import fr.eql.ai116.proj2.tim.entity.dto.BankCardDto;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote(BankCardBusiness.class)
@Stateless
public class BankCardBusinessImpl implements BankCardBusiness {

    @EJB
    private BankCardDao bankCardDao;

    @Override
    public void addBankCard(BankCardDto bankCardDto) {
        System.out.println("User ID "+bankCardDto.getUserId());
        BankCard bankCard = new BankCard(bankCardDto.getNumberCard(),bankCardDto.getCardHolderName(),
                bankCardDto.getExpirationDate(), bankCardDto.getCvvNumber(), null);
        bankCardDao.addBankCard(bankCard, bankCardDto.getUserId());
    }
}
