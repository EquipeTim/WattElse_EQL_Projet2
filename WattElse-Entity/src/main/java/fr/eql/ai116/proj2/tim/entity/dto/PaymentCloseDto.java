package fr.eql.ai116.proj2.tim.entity.dto;

import java.io.Serializable;

public class PaymentCloseDto implements Serializable {

    private Long idBankAccount;
    private Long bankCardId;



    public PaymentCloseDto() {
    }

    /// Getters//
    public Long getIdBankAccount() {
        return idBankAccount;
    }
    public Long getBankCardId() {
        return bankCardId;
    }

    //Setter///


    public void setIdBankAccount(Long idBankAccount) {
        this.idBankAccount = idBankAccount;
    }
    public void setBankCardId(Long bankCardId) {
        this.bankCardId = bankCardId;
    }
}
