package fr.eql.ai116.proj2.tim.entity;

import java.io.Serializable;
import java.time.LocalDate;

// Transaction
public class Transaction implements Serializable {

    private long idTransaction;
    private final LocalDate registrationReservationDate;
    private final LocalDate reservationDate;
    private final long reservationDuration;
    private final LocalDate startDateCharging;
    private final LocalDate endDateCharging;
    private final LocalDate datePayment;
    private final LocalDate confirmationDateReservation;
    private final LocalDate refuseDateReservation;
    private final LocalDate cancellationDate;
    private final int consumeQuantityPower;
    private final int monetaryAmount;

    //Constructor///


    public Transaction(LocalDate registrationReservationDate, LocalDate reservationDate, long reservationDuration, LocalDate startDateCharging, LocalDate endDateCharging, LocalDate datePayment, LocalDate confirmationDateReservation, LocalDate refuseDateReservation, LocalDate cancellationDate, int consumeQuantityPower, int monetaryAmount, long idTransaction) {
        this.registrationReservationDate = registrationReservationDate;
        this.reservationDate = reservationDate;
        this.reservationDuration = reservationDuration;
        this.startDateCharging = startDateCharging;
        this.endDateCharging = endDateCharging;
        this.datePayment = datePayment;
        this.confirmationDateReservation = confirmationDateReservation;
        this.refuseDateReservation = refuseDateReservation;
        this.cancellationDate = cancellationDate;
        this.consumeQuantityPower = consumeQuantityPower;
        this.monetaryAmount = monetaryAmount;
        this.idTransaction = idTransaction;
    }

    /// Setter///

    public void setIdTransaction(long idTransaction) {
        this.idTransaction = idTransaction;
    }
}
