package fr.eql.ai116.proj2.tim.entity.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class TransactionDto implements Serializable {

    private Long idStation;
    private Long idUser;
    private Timestamp chargeStartTime;
    private Timestamp chargeEndTime;
    private String tarifType;
    private Float tarif;
    private Integer consumedQuantity;
    private Float amountToPay;

    public TransactionDto() {
    }

    public TransactionDto(Long idStation, Long idUser, Timestamp chargeStartTime, Timestamp chargeEndTime,
                          String tarifType, Float tarif, Integer consumedQuantity) {
        this.idStation = idStation;
        this.idUser = idUser;
        this.chargeStartTime = chargeStartTime;
        this.chargeEndTime = chargeEndTime;
        this.tarifType = tarifType;
        this.tarif = tarif;
        this.consumedQuantity = consumedQuantity;
        this.amountToPay = tarif * consumedQuantity;
    }

    public Long getIdStation() {
        return idStation;
    }

    public Long getIdUser() {
        return idUser;
    }

    public Timestamp getChargeStartTime() {
        return chargeStartTime;
    }

    public Timestamp getChargeEndTime() {
        return chargeEndTime;
    }

    public String getTarifType() {
        return tarifType;
    }

    public Float getTarif() {
        return tarif;
    }

    public Integer getConsumedQuantity() {
        return consumedQuantity;
    }

    public Float getAmountToPay() {
        return amountToPay;
    }
}
