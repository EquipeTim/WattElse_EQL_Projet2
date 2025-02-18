package fr.eql.ai116.proj2.tim.dao;

import fr.eql.ai116.proj2.tim.entity.Reservation;
import fr.eql.ai116.proj2.tim.entity.Transaction;
import fr.eql.ai116.proj2.tim.entity.dto.ReservationDto;

// Transaction
public interface TransactionDao {
    Reservation reserveStation(ReservationDto reservationDto);
    Transaction startCharging(Long reservationId);
    Transaction stopCharging(Long reservationId);
    Transaction generateTransactionInfo(Long reservationId);

}
