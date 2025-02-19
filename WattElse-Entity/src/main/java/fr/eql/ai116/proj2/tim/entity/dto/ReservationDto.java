package fr.eql.ai116.proj2.tim.entity.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReservationDto implements Serializable {

    private Long idReservation;
    private Long idStation;
    private Long idUser;
    private Long idUserBankCard;
    private Long idUserBankAccount;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private String timeZone;
    private int reservationDuration;
    private Timestamp chargeStart;
    private Timestamp chargeEnd;

    ///  Setters ///

    public void setIdUserBankCard(Long idUserBankCard) {
        this.idUserBankCard = idUserBankCard;
    }

    public void setIdUserBankAccount(Long idUserBankAccount) {
        this.idUserBankAccount = idUserBankAccount;
    }

    public void setIdStation(Long idStation) {
        this.idStation = idStation;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public void setReservationTime(LocalTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public void setReservationDuration(int reservationDuration) {
        this.reservationDuration = reservationDuration;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public void setChargeStart(Timestamp chargeStart) {
        this.chargeStart = chargeStart;
    }

    public void setChargeEnd(Timestamp chargeEnd) {
        this.chargeEnd = chargeEnd;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /// Getters ///

    public Long getIdStation() {
        return idStation;
    }

    public Long getIdUser() {
        return idUser;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate.atTime(reservationTime);
    }

    public String getTimeZone() {
        return timeZone;
    }

    public int getReservationDuration() {
        return reservationDuration;
    }

    public Long getIdUserBankCard() {
        return idUserBankCard;
    }

    public Long getIdUserBankAccount() {
        return idUserBankAccount;
    }

    public Long getIdReservation() {
        return idReservation;
    }

    public Timestamp getChargeStart() {
        return chargeStart;
    }

    public Timestamp getChargeEnd() {
        return chargeEnd;
    }
}
