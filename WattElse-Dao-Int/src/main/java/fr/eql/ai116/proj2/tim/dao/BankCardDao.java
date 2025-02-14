package fr.eql.ai116.proj2.tim.dao;

import fr.eql.ai116.proj2.tim.entity.BankCard;

import java.util.List;

// Carte de Credit
public interface BankCardDao {
    void addBankCard(BankCard bankCard, Long userId);
    void closeBankCard(BankCard bankCard);
    void modifyBankCard(BankCard bankCard);
    List<BankCard> getBankCards(long userId);
}
