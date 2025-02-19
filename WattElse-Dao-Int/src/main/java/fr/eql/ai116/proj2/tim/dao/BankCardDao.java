package fr.eql.ai116.proj2.tim.dao;

import fr.eql.ai116.proj2.tim.entity.BankCard;
import fr.eql.ai116.proj2.tim.entity.User;


import java.util.List;

// Carte de Credit
public interface BankCardDao {
    boolean registerCard(BankCard bankCard, Long userId);
    boolean closeBankCard(Long bankCardId);
    boolean modifyBankCard(BankCard bankCard);
    List<BankCard> getBankCards(String token);
    BankCard getBankCardById(Long bankCardId);


}
