package fr.eql.ai116.proj2.tim.entity.dto;

import com.sun.javafx.image.impl.ByteIndexed;

import java.io.Serializable;

public class CarCloseDto implements Serializable {
    private Long idCar;
    private Long reasonId;

    //Constructor vide

    public CarCloseDto() {
    }


    //Getters


    public Long getIdCar() {
        return idCar;
    }

    public Long getReasonId() {
        return reasonId;
    }

    //Setters


    public void setIdCar(Long idCar) {
        this.idCar = idCar;
    }

    public void setReasonId(Long reasonId) {
        this.reasonId = reasonId;
    }
}
