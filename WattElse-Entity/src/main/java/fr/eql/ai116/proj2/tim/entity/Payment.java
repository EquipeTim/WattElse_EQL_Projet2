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
    private Long idOwner;
    private LocalDateTime paymentDate;
    private LocalDateTime reservationDate;
    private String nrAccountUsed;
    private String nrCardUsed;
    private String paymentRefuseReason;
    private Float amountToPay;

    public Payment(Long idAccountUsed, Long idCardUsed, Long idPaymentRefuseReason, Long idReservation,
                   Long idUser, Long idOwner, LocalDateTime paymentDate, LocalDateTime reservationDate,
                   String nrAccountUsed, String nrCardUsed, String paymentRefuseReason, Float amountToPay) {
        this.idAccountUsed = idAccountUsed;
        this.idCardUsed = idCardUsed;
        this.idPaymentRefuseReason = idPaymentRefuseReason;
        this.idReservation = idReservation;
        this.idUser = idUser;
        this.idOwner = idOwner;
        this.paymentDate = paymentDate;
        this.reservationDate = reservationDate;
        this.nrAccountUsed = nrAccountUsed;
        this.nrCardUsed = nrCardUsed;
        this.paymentRefuseReason = paymentRefuseReason;
        this.amountToPay = amountToPay;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public Long getIdAccountUsed() {
        return idAccountUsed;
    }
}
