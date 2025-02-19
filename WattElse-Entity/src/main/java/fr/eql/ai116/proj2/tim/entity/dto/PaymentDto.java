package fr.eql.ai116.proj2.tim.entity.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class PaymentDto implements Serializable {

    private Long idTransaction;
    private Long idCardForPayment;
    private Long idAccountForPayment;

    public Long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Long idTransaction) {
        this.idTransaction = idTransaction;
    }

    public Long getIdCardForPayment() {
        return idCardForPayment;
    }

    public void setIdCardForPayment(Long idCardForPayment) {
        this.idCardForPayment = idCardForPayment;
    }

    public Long getIdAccountForPayment() {
        return idAccountForPayment;
    }

    public void setIdAccountForPayment(Long idAccountForPayment) {
        this.idAccountForPayment = idAccountForPayment;
    }
}
