package fr.eql.ai116.proj2.tim.entity.dto;

import java.io.Serializable;

public class PaymentDto implements Serializable {

    private Long idReservation;
    private Long idCardForPayment;
    private Long idAccountForPayment;


    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
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


    @Override
    public String toString() {
        return "PaymentDto{" +
                "idReservation=" + idReservation +
                ", idCardForPayment=" + idCardForPayment +
                ", idAccountForPayment=" + idAccountForPayment +
                '}';
    }
}
