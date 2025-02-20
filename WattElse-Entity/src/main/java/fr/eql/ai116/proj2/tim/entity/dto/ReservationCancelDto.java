package fr.eql.ai116.proj2.tim.entity.dto;

import java.io.Serializable;

public class ReservationCancelDto implements Serializable {
    private Long idCancelReason;
    private Long idReservation;

    public Long getIdCancelReason() {
        return idCancelReason;
    }

    public void setIdCancelReason(Long idCancelReason) {
        this.idCancelReason = idCancelReason;
    }

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }
}
