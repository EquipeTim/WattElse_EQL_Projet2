package fr.eql.ai116.proj2.tim.dao;

import fr.eql.ai116.proj2.tim.entity.CreditCard;

import java.util.List;

// Carte de Credit
public interface CreditCardDao {
    void addCreditCard(CreditCard creditCard, long userId);
    void closeCreditCard(CreditCard creditCard);
    void modifyCreditCard(CreditCard creditCard);
    List<CreditCard> getCreditCards(long userId);
}
