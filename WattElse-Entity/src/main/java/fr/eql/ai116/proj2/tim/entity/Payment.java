package fr.eql.ai116.proj2.tim.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

// Reglement
public class Payment implements Serializable {
    private Long idAccountUsed;
    private Long idCardUsed;
    private Long idPaymentRefuseReason;
    private Long idReservation;
    private Long idUser;
    private LocalDateTime paymentDate;
    private LocalDateTime reservationDate;
    private String nrAccountUsed;
    private String nrCardUsed;
    private String paymentRefuseReason;
    private Float amountToPay;

    public Payment(Long idAccountUsed, Long idCardUsed, Long idPaymentRefuseReason, Long idReservation,
                   Long idUser, LocalDateTime paymentDate, LocalDateTime reservationDate,
                   String nrAccountUsed, String nrCardUsed, String paymentRefuseReason, Float amountToPay) {
        this.idAccountUsed = idAccountUsed;
        this.idCardUsed = idCardUsed;
        this.idPaymentRefuseReason = idPaymentRefuseReason;
        this.idReservation = idReservation;
        this.idUser = idUser;
        this.paymentDate = paymentDate;
        this.reservationDate = reservationDate;
        this.nrAccountUsed = nrAccountUsed;
        this.nrCardUsed = nrCardUsed;
        this.paymentRefuseReason = paymentRefuseReason;
        this.amountToPay = amountToPay;
    }

    public Long getIdAccountUsed() {
        return idAccountUsed;
    }

    public Long getIdCardUsed() {
        return idCardUsed;
    }

    public Long getIdPaymentRefuseReason() {
        return idPaymentRefuseReason;
    }

    public Long getIdReservation() {
        return idReservation;
    }

    public Long getIdUser() {
        return idUser;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public String getNrAccountUsed() {
        return nrAccountUsed;
    }

    public String getNrCardUsed() {
        return nrCardUsed;
    }

    public String getPaymentRefuseReason() {
        return paymentRefuseReason;
    }

    public Float getAmountToPay() {
        return amountToPay;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "idAccountUsed=" + idAccountUsed +
                ", idCardUsed=" + idCardUsed +
                ", idPaymentRefuseReason=" + idPaymentRefuseReason +
                ", idReservation=" + idReservation +
                ", idUser=" + idUser +
                ", paymentDate=" + paymentDate +
                ", reservationDate=" + reservationDate +
                ", nrAccountUsed='" + nrAccountUsed + '\'' +
                ", nrCardUsed='" + nrCardUsed + '\'' +
                ", paymentRefuseReason='" + paymentRefuseReason + '\'' +
                ", amountToPay=" + amountToPay +
                '}';
    }
}
