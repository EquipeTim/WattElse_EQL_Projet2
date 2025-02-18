package fr.eql.ai116.proj2.tim.business;


import fr.eql.ai116.proj2.tim.entity.dto.ChoicesDto;
import fr.eql.ai116.proj2.tim.entity.dto.ReservationDto;


public interface TransactionBusiness {
    ReservationDto reserveStation(ReservationDto reservationDto);
    ChoicesDto indicateStartCharging(ReservationDto reservationDto);
    ChoicesDto indicateStopCharging(ReservationDto reservationDto);

}
