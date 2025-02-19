package fr.eql.ai116.proj2.tim.entity.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class BankCardDto implements Serializable {

    private long idCreditCard;
    private String numberCard;
    private String cardHolderName;
    private LocalDate expirationDate;
    private int cvvNumber;
    private long userId;

    public long getIdCreditCard() {return idCreditCard;}

    public void setIdCreditCard(long idCreditCard) {this.idCreditCard = idCreditCard;}

    public String getNumberCard() {return numberCard;}

    public void setNumberCard(String numberCard) {this.numberCard = numberCard;}

    public String getCardHolderName() {return cardHolderName;}

    public void setCardHolderName(String cardHolderName) {this.cardHolderName = cardHolderName;}

    public LocalDate getExpirationDate() {return expirationDate;}

    public void setExpirationDate(LocalDate expirationDate) {this.expirationDate = expirationDate;}

    public int getCvvNumber() {return cvvNumber;}

    public void setCvvNumber(int cvvNumber) {this.cvvNumber = cvvNumber;}

    public long getUserId() {return userId;}

    public void setUserId(long userId) {this.userId = userId;}

    @Override
    public String toString() {
        return "BankCardDto{" +
                "idCreditCard=" + idCreditCard +
                ", numberCard='" + numberCard + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", expirationDate=" + expirationDate +
                ", cvvNumber=" + cvvNumber +
                ", userId=" + userId +
                '}';
    }
}
