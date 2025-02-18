package fr.eql.ai116.proj2.tim.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private static final int OVERDUE_ALLOWED = 15;
    private Long userId;
    private Long reservationId;
    private Timestamp reservationTime;
    private int reservationDuration;
    private Timestamp rechargeStartTime;
    private Timestamp rechargeEndTime;

    public Reservation(){}

    public Reservation(Long userId, Long reservationId, Timestamp reservationTime,
                       int reservationDuration, Timestamp rechargeStartTime, Timestamp rechargeEndTime) {
        this.userId = userId;
        this.reservationId = reservationId;
        this.reservationTime = reservationTime;
        this.reservationDuration = reservationDuration;
        this.rechargeStartTime = rechargeStartTime;
        this.rechargeEndTime = rechargeEndTime;
    }

    /**
     * Checks if reservation is valid; Allows 15 min late
     * @param time
     * @return true if time indicated is within the reservation duration
     */
    public boolean reservationValid(Timestamp time){
        long timePassed_ms = time.getTime() -reservationTime.getTime();

        long timePassed_min = ((timePassed_ms/1000)) / 60;
        if (timePassed_min - OVERDUE_ALLOWED < reservationDuration){
            return true;
        }
        return false;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public void setReservationTime(Timestamp reservationTime) {
        this.reservationTime = reservationTime;
    }

    public void setReservationDuration(int reservationDuration) {
        this.reservationDuration = reservationDuration;
    }

    ///  Getters ///
    public Timestamp getRechargeStartTime() {
        return rechargeStartTime;
    }

    public Timestamp getRechargeEndTime() {
        return rechargeEndTime;
    }


}
