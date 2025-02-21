package fr.eql.ai116.proj2.tim.controller;

import fr.eql.ai116.proj2.tim.business.TransactionBusiness;
import fr.eql.ai116.proj2.tim.entity.Payment;
import fr.eql.ai116.proj2.tim.entity.Reservation;
import fr.eql.ai116.proj2.tim.entity.Transaction;
import fr.eql.ai116.proj2.tim.entity.dto.ChoicesDto;
import fr.eql.ai116.proj2.tim.entity.dto.FullUserDto;
import fr.eql.ai116.proj2.tim.entity.dto.PaymentDto;
import fr.eql.ai116.proj2.tim.entity.dto.ReservationDto;
import fr.eql.ai116.proj2.tim.entity.dto.SearchDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Stateless
@Path("/transaction")
public class TransactionController {

    @EJB
    TransactionBusiness transactionBusiness;

    @POST
    @Path("/reservation")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response reserve(ReservationDto reservationDto) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of(reservationDto.getTimeZone()));
        if (reservationDto.getReservationDate().isAfter(now)
                && (reservationDto.getIdUserBankAccount() != null
                    || reservationDto.getIdUserBankCard() != null)) {
            Reservation reservation = transactionBusiness.reserveStation(reservationDto);
            if (reservation != null) {
                return Response.ok(reservation).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Path("/start")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response startCharge(ReservationDto reservationDto) {
        Transaction status = transactionBusiness.indicateStartCharging(reservationDto);
        return Response.ok(status).build();
    }

    @POST
    @Path("/stop")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response stopCharge(ReservationDto reservationDto) {
        Transaction status = transactionBusiness.indicateStopCharging(reservationDto);
        return Response.ok(status).build();
    }

    @GET
    @Path("/info/reservation/{reservation_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionInfo(@PathParam("reservation_id") Long reservationId) {
        Transaction status = transactionBusiness.getTransactionDetails(reservationId);
        if (status != null) {
            return Response.ok(status).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/info/user/history/reservations")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUserTransactions(SearchDto searchDto) {
        List<Transaction> transactions = transactionBusiness.getUserTransactions(searchDto);
        return Response.ok(transactions).build();
    }

    @POST
    @Path("/info/user/history/payments")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUserPayments(SearchDto searchDto) {
        List<Payment> payments = transactionBusiness.getUserPayments(searchDto);
        return Response.ok( payments).build();
    }

    @POST
    @Path("/pay")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pay(PaymentDto paymentDto) {
        if (paymentDto.getIdAccountForPayment() != null
           || paymentDto.getIdCardForPayment() != null){
                Payment payment = transactionBusiness.pay(paymentDto);
                return Response.ok(payment).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();

    }

}
