package fr.eql.ai116.proj2.tim.controller;

import fr.eql.ai116.proj2.tim.business.AuthenticationException;
import fr.eql.ai116.proj2.tim.business.UserBusiness;
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
public class UserController {

    @EJB
    UserBusiness userBusiness;

    /**
     * Registers user to database
     * @param fullUserDto
     * @return code: 201 if successful 302 if already exists
     */
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(FullUserDto fullUserDto) {
        try {
            boolean added = userBusiness.registerUser(fullUserDto);
            if (added) {
                return Response.status(Response.Status.CREATED).build();
            } else {
                return Response.status(Response.Status.FOUND).build();
            }
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
