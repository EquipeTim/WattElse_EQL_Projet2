package fr.eql.ai116.proj2.tim.business;

import fr.eql.ai116.proj2.tim.entity.BankCard;
import fr.eql.ai116.proj2.tim.entity.dto.BankCardDto;

import java.util.List;

public interface BankCardBusiness {
    void addBankCard(BankCardDto bankCardDto);
    List<BankCard> getBankCards(String token);
}
