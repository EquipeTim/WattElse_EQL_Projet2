package fr.eql.ai116.proj2.tim.entity.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class BankAccountDto implements Serializable {
    private Long idAccount;
    private String iban;
    private String ownerName;
    private String swift;
    private Long userId;

    

    public Long getIdAccount() {return idAccount;}

    public void setIdAccount(long idAccount) {this.idAccount = idAccount;}

    public String getIban() {return iban;}

    public void setIban(String iban) {this.iban = iban;}

    public String getOwnerName() {return ownerName;}

    public void setOwnerName(String account_owner_name) {
        this.ownerName = account_owner_name;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
