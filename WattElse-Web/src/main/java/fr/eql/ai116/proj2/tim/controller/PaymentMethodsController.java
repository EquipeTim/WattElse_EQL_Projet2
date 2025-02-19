package fr.eql.ai116.proj2.tim.controller;

import fr.eql.ai116.proj2.tim.business.BankAccountBusiness;
import fr.eql.ai116.proj2.tim.business.BankCardBusiness;
import fr.eql.ai116.proj2.tim.entity.BankAccount;
import fr.eql.ai116.proj2.tim.entity.BankCard;
import fr.eql.ai116.proj2.tim.entity.dto.BankAccountDto;
import fr.eql.ai116.proj2.tim.entity.dto.BankCardDto;
import fr.eql.ai116.proj2.tim.entity.dto.FullUserDto;
import fr.eql.ai116.proj2.tim.entity.dto.PaymentCloseDto;

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
    public Response register(BankCardDto bankCardDto) {
        boolean added = bankCardBusiness.registerCard(bankCardDto, bankCardDto.getUserId());
        if (added) {
            return Response.status(Response.Status.CREATED).build();
        }else{
            return Response.status(Response.Status.FOUND).build();
        }
    }

    @POST
    @Path("/account/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(BankAccountDto bankAccountDto) {
        boolean added = bankAccountBusiness.registerBankAccount(bankAccountDto,bankAccountDto.getUserId());
        if (added) {
            return Response.status(Response.Status.CREATED).build();
        }else{
            return Response.status(Response.Status.FOUND).build();
        }
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
    @GET
    @Path("/card/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchUserCards(@Context HttpHeaders headers) {
        String authorizationHeader = headers.getHeaderString("Authorization");
        String token = authorizationHeader.substring("Bearer ".length());
        List<BankCard> cards = bankCardBusiness.getBankCards(token);
        return Response.ok(cards).build();
    }



    @POST
    @Path("/card/close")
    @Produces(MediaType.APPLICATION_JSON)
    public Response close(PaymentCloseDto paymentCloseDto) {
    boolean isClosed = bankCardBusiness.closeBankCard(paymentCloseDto, paymentCloseDto.getBankCardId());
        if (isClosed) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
}
    @POST
    @Path("/account/close")
    @Produces(MediaType.APPLICATION_JSON)
    public Response closeaccount(PaymentCloseDto paymentCloseDto) {
        boolean isClosed = bankAccountBusiness.closeBankAccount(paymentCloseDto, paymentCloseDto.getIdBankAccount());
        if (isClosed) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }


    @POST
    @Path("card/modify")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modify(BankCardDto bankCardDto) {
        boolean updated = bankCardBusiness.modifyBankCard(bankCardDto);
        if (updated) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}

