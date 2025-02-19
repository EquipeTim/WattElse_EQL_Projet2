package fr.eql.ai116.proj2.tim.business.impl;

import fr.eql.ai116.proj2.tim.business.BankCardBusiness;
import fr.eql.ai116.proj2.tim.business.UserBusiness;
import fr.eql.ai116.proj2.tim.dao.BankCardDao;
import fr.eql.ai116.proj2.tim.entity.BankCard;
import fr.eql.ai116.proj2.tim.entity.dto.BankCardDto;
import fr.eql.ai116.proj2.tim.entity.dto.PaymentCloseDto;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Remote(BankCardBusiness.class)
@Stateless
public class BankCardBusinessImpl implements BankCardBusiness {

    @EJB
    private BankCardDao bankCardDao;


    @Override
    public boolean registerCard(BankCardDto bankCardDto, Long userId) {
        BankCard bankCard = new BankCard(bankCardDto.getNumberCard(),bankCardDto.getCardHolderName(),
                bankCardDto.getExpirationDate(), bankCardDto.getCvvNumber(), null);
        return bankCardDao.registerCard(bankCard, userId);
    }

    @Override
    public List<BankCard> getBankCards(String token) {
        return bankCardDao.getBankCards(token);
    }

    @Override
    public boolean closeBankCard(PaymentCloseDto paymentCloseDto, Long userId) {
        BankCard bankCard = bankCardDao.getBankCardById(paymentCloseDto.getBankCardId());

        if(bankCard != null) {
            bankCardDao.closeBankCard(paymentCloseDto.getBankCardId());
            return true;
        }
        return false;
        }

}


