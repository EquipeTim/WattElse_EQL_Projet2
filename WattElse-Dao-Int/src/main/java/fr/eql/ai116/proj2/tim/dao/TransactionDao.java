package fr.eql.ai116.proj2.tim.dao;

import fr.eql.ai116.proj2.tim.entity.OpeningHour;
import fr.eql.ai116.proj2.tim.entity.Payment;
import fr.eql.ai116.proj2.tim.entity.Reservation;
import fr.eql.ai116.proj2.tim.entity.Revenue;
import fr.eql.ai116.proj2.tim.entity.Transaction;
import fr.eql.ai116.proj2.tim.entity.dto.PaymentDto;
import fr.eql.ai116.proj2.tim.entity.dto.ReservationDto;

import java.time.LocalDate;
import java.util.List;

// Transaction
public interface TransactionDao {
    Reservation reserveStation(ReservationDto reservationDto);
    Transaction startCharging(Long reservationId);
    Transaction stopCharging(Long reservationId);
    Transaction generateTransactionInfo(Long reservationId);
    List<Transaction> getUserTransactions(Long userId, String date);
    Payment pay(PaymentDto paymentDto);
    boolean cancelReservation(Long reservationId, Long reasonId);
    List<Payment> getPayments(Long userId, String date);
    List<Revenue> getUserRevenues(Long userId, String date, String token);


}
