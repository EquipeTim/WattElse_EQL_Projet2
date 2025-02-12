package fr.eql.ai116.proj2.tim.entity;

import java.time.LocalDate;

// Reglement
public class Payment {

    private long idPayment;
    private final LocalDate paymentDate;
    private final int paymentAmount;

    /// Constructors///
    public Payment(LocalDate paymentDate, int paymentAmount, long idPayment) {
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.idPayment = idPayment;
    }

    //Setter ///

    public void setIdPayment(long idPayment) {
        this.idPayment = idPayment;
    }
}
