package fr.eql.ai116.proj2.tim.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Reservation implements Serializable {
    private static final int OVERDUE_ALLOWED = 15;
    private Long userId;
    private Long reservationId;
    private Timestamp reservationTime;
    private int reservationDuration;
    private Timestamp rechargeStartTime;
    private Timestamp rechargeEndTime;
    private Timestamp reservationCancelTime;


    public Reservation(){}

    public Reservation(Long userId, Long reservationId, Timestamp reservationTime,
                       int reservationDuration, Timestamp rechargeStartTime, Timestamp rechargeEndTime,
                       Timestamp reservationCancelTime) {
        this.userId = userId;
        this.reservationId = reservationId;
        this.reservationTime = reservationTime;
        this.reservationDuration = reservationDuration;
        this.rechargeStartTime = rechargeStartTime;
        this.rechargeEndTime = rechargeEndTime;
        this.reservationCancelTime = reservationCancelTime;
    }

    /**
     * Checks if reservation is valid; Allows 15 min late
     * @param time
     * @return 1 if time indicated is within 15 min of reservation( +/- 15 min from reservation time),
     * -1 : indicated below the reserved time window
     * 0 : indicated time is above the reserved time window
     */
    public int reservationValid(Timestamp time){
        long timePassed_ms = time.getTime() -reservationTime.getTime();

        long timePassed_min = ((timePassed_ms/1000)) / 60;
        if (timePassed_min < OVERDUE_ALLOWED && timePassed_min > -OVERDUE_ALLOWED){
            return 1;
        }
        if (timePassed_min < -OVERDUE_ALLOWED) {return -1;}
        return 0;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public void setReservationTime(Timestamp reservationTime) {
        this.reservationTime = reservationTime;
    }

    /**
     * Set reservation duration in minutes
     * @param reservationDuration
     */
    public void setReservationDuration(int reservationDuration) {
        this.reservationDuration = reservationDuration;
    }

    ///  Getters ///
    public Timestamp getRechargeStartTime() {
        return rechargeStartTime;
    }

    public Timestamp getReservationTime() {
        return reservationTime;
    }

    public Timestamp getRechargeEndTime() {
        return rechargeEndTime;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public int getReservationDuration() {
        return reservationDuration;
    }

    public Timestamp getReservationCancelTime() {
        return reservationCancelTime;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "userId=" + userId +
                ", reservationId=" + reservationId +
                ", reservationTime=" + reservationTime +
                ", reservationDuration=" + reservationDuration +
                ", rechargeStartTime=" + rechargeStartTime +
                ", rechargeEndTime=" + rechargeEndTime +
                '}';
    }
}
