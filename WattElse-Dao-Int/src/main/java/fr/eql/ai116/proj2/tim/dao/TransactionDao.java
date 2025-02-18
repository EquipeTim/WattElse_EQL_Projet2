package fr.eql.ai116.proj2.tim.dao;

import fr.eql.ai116.proj2.tim.entity.dto.ReservationDto;

// Transaction
public interface TransactionDao {
    void reserveStation(ReservationDto reservationDto);

}
