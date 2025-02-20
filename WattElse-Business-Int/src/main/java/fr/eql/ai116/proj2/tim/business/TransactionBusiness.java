package fr.eql.ai116.proj2.tim.business;


import fr.eql.ai116.proj2.tim.entity.Reservation;
import fr.eql.ai116.proj2.tim.entity.Transaction;
import fr.eql.ai116.proj2.tim.entity.dto.ChoicesDto;
import fr.eql.ai116.proj2.tim.entity.dto.PaymentDto;
import fr.eql.ai116.proj2.tim.entity.dto.ReservationDto;
import fr.eql.ai116.proj2.tim.entity.dto.SearchDto;

import java.util.List;


public interface TransactionBusiness {
    Reservation reserveStation(ReservationDto reservationDto);
    Transaction indicateStartCharging(ReservationDto reservationDto);
    Transaction indicateStopCharging(ReservationDto reservationDto);
    Transaction getTransactionDetails(Long reservationId);
    List<Transaction> getUserTransactions(SearchDto searchDto);
    void pay(PaymentDto paymentDto);

}
