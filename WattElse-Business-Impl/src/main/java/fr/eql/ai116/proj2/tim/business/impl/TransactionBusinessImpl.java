package fr.eql.ai116.proj2.tim.business.impl;

import fr.eql.ai116.proj2.tim.business.TransactionBusiness;
import fr.eql.ai116.proj2.tim.dao.TransactionDao;
import fr.eql.ai116.proj2.tim.entity.Payment;
import fr.eql.ai116.proj2.tim.entity.Reservation;
import fr.eql.ai116.proj2.tim.entity.Transaction;
import fr.eql.ai116.proj2.tim.entity.dto.ChoicesDto;
import fr.eql.ai116.proj2.tim.entity.dto.PaymentDto;
import fr.eql.ai116.proj2.tim.entity.dto.ReservationCancelDto;
import fr.eql.ai116.proj2.tim.entity.dto.ReservationDto;
import fr.eql.ai116.proj2.tim.entity.dto.SearchDto;
import fr.eql.ai116.proj2.tim.entity.dto.UserDto;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.swing.plaf.IconUIResource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Remote(TransactionBusiness.class)
@Stateless
public class TransactionBusinessImpl implements TransactionBusiness {

    @EJB
    private TransactionDao transactionDao;

    @Override
    public Reservation reserveStation(ReservationDto reservationDto) {
        return transactionDao.reserveStation(reservationDto);
    }

    @Override
    public boolean cancelReservation(ReservationCancelDto reservationCancelDto) {
        return transactionDao.cancelReservation(reservationCancelDto.getIdReservation(),
                reservationCancelDto.getIdCancelReason());
    }

    @Override
    public Transaction indicateStartCharging(ReservationDto reservationDto) {
        return transactionDao.startCharging(reservationDto.getIdReservation());
    }

    @Override
    public Transaction indicateStopCharging(ReservationDto reservationDto) {
        return transactionDao.stopCharging(reservationDto.getIdReservation());
    }

    @Override
    public Transaction getTransactionDetails(Long reservationId) {
        return transactionDao.generateTransactionInfo(reservationId);
    }

    @Override
    public List<Transaction> getUserTransactions(SearchDto searchDto) {
        return transactionDao.getUserTransactions(searchDto.getUserId(), searchDto.getDate());
    }

    @Override
    public Payment pay(PaymentDto paymentDto) {
        return transactionDao.pay(paymentDto);
    }

    @Override
    public List<Payment> getUserPayments(SearchDto searchDto) {
        return transactionDao.getPayments(searchDto.getUserId(), searchDto.getDate());
    }
}
