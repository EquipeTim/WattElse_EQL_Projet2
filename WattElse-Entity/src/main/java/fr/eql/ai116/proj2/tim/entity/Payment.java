package fr.eql.ai116.proj2.tim.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

// Reglement
public class Payment extends Transaction implements Serializable {

    public Payment(long idTransaction, Long idUser, Long idOwner, LocalDateTime startDateCharging,
                   LocalDateTime endDateCharging, Float consumeQuantity, String priceType, Float price) {
        super(idTransaction, idUser, idOwner, startDateCharging, endDateCharging, consumeQuantity, priceType, price);
    }
}
