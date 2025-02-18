package fr.eql.ai116.proj2.tim.entity.dto;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationDto implements Serializable {

    private Long idReservation;
    private Long idStation;
    private Long idUser;
    private Long idUserBankCard;
    private Long idUserBankAccount;
    private LocalDate reservationDate;
    private LocalTime reservationStart;
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

    public void setReservationStart(LocalTime reservationStart) {
        this.reservationStart = reservationStart;
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

    /// Getters ///

    public Long getIdStation() {
        return idStation;
    }

    public Long getIdUser() {
        return idUser;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public LocalTime getReservationStart() {
        return reservationStart;
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
