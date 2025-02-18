package fr.eql.ai116.proj2.tim.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private static final int OVERDUE_ALLOWED = 15;
    private final Long userId;
    private final Long reservationId;
    private final Timestamp reservationTime;
    private final int reservationDuration;
    private final Timestamp rechargeStartTime;
    private final Timestamp rechargeEndTime;


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

    public Timestamp getRechargeStartTime() {
        return rechargeStartTime;
    }

    public Timestamp getRechargeEndTime() {
        return rechargeEndTime;
    }
}
