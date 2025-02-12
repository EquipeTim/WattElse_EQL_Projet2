package fr.eql.ai116.proj2.tim.controller;

import fr.eql.ai116.proj2.tim.business.AuthenticationException;
import fr.eql.ai116.proj2.tim.business.SecurityBusiness;
import fr.eql.ai116.proj2.tim.entity.dto.CredentialsDto;
import fr.eql.ai116.proj2.tim.entity.dto.UserDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/connection")
public class ConnectionController {

    @EJB
    SecurityBusiness securityBusiness;

    @POST
    @Path("/authenticate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(CredentialsDto credentialsDto) {
        try {
            UserDto userDto = securityBusiness.authenticate(credentialsDto.getEmail(), credentialsDto.getPassword());
            return Response.ok(userDto).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
