package fr.eql.ai116.proj2.tim.entity.dto;

import java.time.LocalDate;

public class CreditCardDto {private long idCreditCard;
    private long numberCard;
    private String cardHolderName;
    private LocalDate expirationDate;
    private int cvvNumber;
    private long cardId;

    public long getIdCreditCard() {return idCreditCard;}

    public void setIdCreditCard(long idCreditCard) {this.idCreditCard = idCreditCard;}

    public long getNumberCard() {return numberCard;}

    public void setNumberCard(long numberCard) {this.numberCard = numberCard;}

    public String getCardHolderName() {return cardHolderName;}

    public void setCardHolderName(String cardHolderName) {this.cardHolderName = cardHolderName;}

    public LocalDate getExpirationDate() {return expirationDate;}

    public void setExpirationDate(LocalDate expirationDate) {this.expirationDate = expirationDate;}

    public int getCvvNumber() {return cvvNumber;}

    public void setCvvNumber(int cvvNumber) {this.cvvNumber = cvvNumber;}

    public long getCardId() {return cardId;}

    public void setCardId(long cardId) {this.cardId = cardId;}
}
