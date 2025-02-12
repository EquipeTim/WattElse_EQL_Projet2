package fr.eql.ai116.proj2.tim.entity;

import java.time.LocalDate;

// Carte de Credit
public class CreditCard {

    private long idCreditCard;
    private final long numberCard;
    private final String cardHolderName;
    private final LocalDate expirationDate;
    private final long cvvNumber;
    private final LocalDate registrationDateCard;
    private final LocalDate withdrawalDateCard;

    //Constructor///


    public CreditCard(long numberCard, String cardHolderName, LocalDate expirationDate, long cvvNumber, LocalDate registrationDateCard, LocalDate withdrawalDateCard, long idCreditCard) {
        this.numberCard = numberCard;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.cvvNumber = cvvNumber;
        this.registrationDateCard = registrationDateCard;
        this.withdrawalDateCard = withdrawalDateCard;
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

    public LocalDate getRegistrationDateCard() {
        return registrationDateCard;
    }

    public LocalDate getWithdrawalDateCard() {
        return withdrawalDateCard;
    }

    //Setters/////
}
