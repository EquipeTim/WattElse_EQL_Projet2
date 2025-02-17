package fr.eql.ai116.proj2.tim.entity;

import java.io.Serializable;
import java.time.LocalDate;

// Compte bancaire
public class BankAccount implements Serializable {
    private Long idBankAccount;
    private final String iban;
    private final String ownerName;
    private final String bicSwift;

/// /Constructor////
    public BankAccount(String iban, String ownerName, String bicSwift, Long idBankAccount) {
        this.idBankAccount = idBankAccount;
        this.iban = iban;
        this.ownerName = ownerName;
        this.bicSwift = bicSwift;

    }
/// //Getters /////
    public Long getIdBankAccount() {
        return idBankAccount;
    }
    public String getIban() {
        return iban;
    }
    public String getAccountOwnerName() {
        return ownerName;
    }
    public String getBicSwift() {
        return bicSwift;
    }

    /// Setters/////

}
