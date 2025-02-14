package fr.eql.ai116.proj2.tim.entity;

import java.io.Serializable;
import java.time.LocalDate;

// Carte de Credit
public class CreditCard implements Serializable {

    private long idCreditCard;
    private final long numberCard;
    private final String cardHolderName;
    private final LocalDate expirationDate;
    private final int cvvNumber;

    //Constructor///


    public CreditCard(long numberCard, String cardHolderName, LocalDate expirationDate,
                      int cvvNumber, long idCreditCard) {
        this.numberCard = numberCard;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.cvvNumber = cvvNumber;
        this.idCreditCard = idCreditCard;
    }

    /// Getters////

    public long getIdCreditCard() {
        return idCreditCard;
    }

    public long getNumberCard() {
        return numberCard;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public long getCvvNumber() {
        return cvvNumber;
    }


    //Setters/////
}
