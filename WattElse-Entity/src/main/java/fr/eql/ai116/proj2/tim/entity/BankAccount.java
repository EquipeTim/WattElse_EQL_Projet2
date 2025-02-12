package fr.eql.ai116.proj2.tim.entity;

import java.time.LocalDate;

// Compte bancaire
public class BankAccount {
    private long idBankAccount;
    private final Long iban;
    private final String cardHolderName;
    private final long bicSwift;
    private final LocalDate startDateRegistrationAccount;
    private final LocalDate closingDateBankAccount;
/// /Constructor////
    public BankAccount(long idBankAccount, Long iban, String cardHolderName, long bicSwift, LocalDate startDateRegistrationAccount, LocalDate closingDateBankAccount) {
        this.idBankAccount = idBankAccount;
        this.iban = iban;
        this.cardHolderName = cardHolderName;
        this.bicSwift = bicSwift;
        this.startDateRegistrationAccount = startDateRegistrationAccount;
        this.closingDateBankAccount = closingDateBankAccount;
    }
/// //Getters /////
    public long getIdBankAccount() {
        return idBankAccount;
    }

    public Long getIban() {
        return iban;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public long getBicSwift() {
        return bicSwift;
    }

    public LocalDate getStartDateRegistrationAccount() {
        return startDateRegistrationAccount;
    }

    public LocalDate getClosingDateBankAccount() {
        return closingDateBankAccount;
    }

    /// Setters/////

}
