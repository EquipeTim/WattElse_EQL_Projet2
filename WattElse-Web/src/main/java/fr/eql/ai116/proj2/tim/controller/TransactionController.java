package fr.eql.ai116.proj2.tim.controller;

import fr.eql.ai116.proj2.tim.business.TransactionBusiness;
import fr.eql.ai116.proj2.tim.entity.dto.ChoicesDto;
import fr.eql.ai116.proj2.tim.entity.dto.FullUserDto;
import fr.eql.ai116.proj2.tim.entity.dto.ReservationDto;

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
        if (reservationDto.getIdUserBankAccount() != null ||
            reservationDto.getIdUserBankCard() != null){
            ReservationDto reservation = transactionBusiness.reserveStation(reservationDto);
        return Response.ok(reservation).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/start")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response startCharge(ReservationDto reservationDto) {
        ChoicesDto status = transactionBusiness.indicateStartCharging(reservationDto);
        return Response.ok(status).build();
    }

    @POST
    @Path("/stop")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response stopCharge(ReservationDto reservationDto) {
        ChoicesDto status = transactionBusiness.indicateStopCharging(reservationDto);
        return Response.ok(status).build();
    }
}
