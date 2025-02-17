package fr.eql.ai116.proj2.tim.controller;

import fr.eql.ai116.proj2.tim.business.BankAccountBusiness;
import fr.eql.ai116.proj2.tim.business.BankCardBusiness;
import fr.eql.ai116.proj2.tim.entity.BankAccount;
import fr.eql.ai116.proj2.tim.entity.Car;
import fr.eql.ai116.proj2.tim.entity.dto.BankAccountDto;
import fr.eql.ai116.proj2.tim.entity.dto.BankCardDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/payment_methods")
public class PaymentMethodsController {

    @EJB
    BankCardBusiness bankCardBusiness;

    @EJB
    BankAccountBusiness bankAccountBusiness;

    @POST
    @Path("/card/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCard(BankCardDto bankCardDto) {
        bankCardBusiness.addBankCard(bankCardDto);
        return Response.ok().build();
    }

    @POST
    @Path("/account/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAccount(BankAccountDto bankAccountDto) {
        bankAccountBusiness.addBankAccount(bankAccountDto);
        return Response.ok().build();
    }

    @GET
    @Path("/account/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchUserCars(@Context HttpHeaders headers) {
        String authorizationHeader = headers.getHeaderString("Authorization");
        String token = authorizationHeader.substring("Bearer ".length());
        if (token != null){
            List<BankAccount> accounts = bankAccountBusiness.getBankAccounts(token);
            return Response.ok(accounts).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
