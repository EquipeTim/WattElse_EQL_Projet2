package fr.eql.ai116.proj2.tim.entity;

import java.io.Serializable;
import java.time.LocalDate;

// Carte de Credit
public class BankCard implements Serializable {

    private Long bankCardId;
    private final String numberCard;
    private final String cardHolderName;
    private final LocalDate expirationDate;
    private final int cvvNumber;

    //Constructor///


    public BankCard(String numberCard, String cardHolderName, LocalDate expirationDate,
                    int cvvNumber, Long idCreditCard) {
        this.numberCard = numberCard;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.cvvNumber = cvvNumber;
        this.bankCardId = idCreditCard;
    }



    /// Getters////

    public long getBankCardId() {
        return bankCardId;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public int getCvvNumber() {
        return cvvNumber;
    }




    //Setters/////


    @Override
    public String toString() {
        return "BankCard{" +
                "bankCardId=" + bankCardId +
                ", numberCard='" + numberCard + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", expirationDate=" + expirationDate +
                ", cvvNumber=" + cvvNumber +
                '}';
    }
}
