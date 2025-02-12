package fr.eql.ai116.proj2.tim.controller;

import fr.eql.ai116.proj2.tim.business.AuthenticationException;
import fr.eql.ai116.proj2.tim.business.RegistrationBusiness;
import fr.eql.ai116.proj2.tim.entity.dto.FullUserDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/registration")
public class RegistrationController {

    @EJB
    RegistrationBusiness registrationBusiness;

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(FullUserDto fullUserDto) {
        try {
            registrationBusiness.registerUser(fullUserDto);
            return Response.ok().build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
