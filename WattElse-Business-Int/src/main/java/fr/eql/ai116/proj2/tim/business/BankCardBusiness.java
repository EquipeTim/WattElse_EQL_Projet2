package fr.eql.ai116.proj2.tim.business;

import fr.eql.ai116.proj2.tim.entity.BankCard;
import fr.eql.ai116.proj2.tim.entity.dto.BankCardDto;
import fr.eql.ai116.proj2.tim.entity.dto.PaymentCloseDto;

import java.util.List;

public interface BankCardBusiness {
    boolean registerCard(BankCardDto bankCardDto, Long userId);
    List<BankCard> getBankCards(String token);
    boolean closeBankCard(PaymentCloseDto paymentCloseDto, Long userId);

}
