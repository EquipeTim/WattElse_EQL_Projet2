package fr.eql.ai116.proj2.tim.controller;

import fr.eql.ai116.proj2.tim.business.TransactionBusiness;
import fr.eql.ai116.proj2.tim.entity.dto.FullUserDto;
import fr.eql.ai116.proj2.tim.entity.dto.ReservationDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/transaction")
public class TransactionController {

    @EJB
    TransactionBusiness transactionBusiness;

    @POST
    @Path("/reservation")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(ReservationDto reservationDto) {
        if (reservationDto.getIdUserBankAccount() != null ||
            reservationDto.getIdUserBankCard() != null){
        transactionBusiness.reserveStation(reservationDto);
        return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
