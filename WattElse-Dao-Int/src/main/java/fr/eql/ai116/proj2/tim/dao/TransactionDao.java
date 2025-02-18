package fr.eql.ai116.proj2.tim.dao;

import fr.eql.ai116.proj2.tim.entity.dto.ChoicesDto;
import fr.eql.ai116.proj2.tim.entity.dto.ReservationDto;
import fr.eql.ai116.proj2.tim.entity.dto.TransactionDto;

// Transaction
public interface TransactionDao {
    ReservationDto reserveStation(ReservationDto reservationDto);
    ChoicesDto startCharging(Long reservationId);
    void stopCharging(Long reservationId);
    TransactionDto generatePayment(Long reservationId);

}
