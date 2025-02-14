package fr.eql.ai116.proj2.tim.controller;

import fr.eql.ai116.proj2.tim.business.BankCardBusiness;
import fr.eql.ai116.proj2.tim.entity.dto.BankCardDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/payment_methods")
public class PaymentMethodsController {

    @EJB
    BankCardBusiness bankCardBusiness;

    @POST
    @Path("/card/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCard(BankCardDto bankCardDto) {
        bankCardBusiness.addBankCard(bankCardDto);
        return Response.ok().build();
    }
}
