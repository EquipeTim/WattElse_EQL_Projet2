package fr.eql.ai116.proj2.tim.controller;


import fr.eql.ai116.proj2.tim.business.SessionNotFoundException;
import fr.eql.ai116.proj2.tim.business.UserBusiness;
import fr.eql.ai116.proj2.tim.entity.dto.FullUserDto;
import fr.eql.ai116.proj2.tim.entity.dto.UserCloseDto;

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
import java.util.SortedMap;

@Stateless
@Path("/user")
public class UserController {

    @EJB
    UserBusiness userBusiness;

    /**
     * Registers user to database
     * @param fullUserDto User Dto containing ALL user info
     * @return code: 201 if added 302 if user already exists
     */
    @POST
    @Path("/registration")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(FullUserDto fullUserDto) {
        boolean added = userBusiness.registerUser(fullUserDto);
        if (added) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.FOUND).build();
        }
    }

    /**
     * Closes the user account in the database
     * @param headers
     * @param userCloseDto
     * @return
     */
    @POST
    @Path("/close")
    @Produces(MediaType.APPLICATION_JSON)
    public Response close(@Context HttpHeaders headers, UserCloseDto userCloseDto) {
        String authorizationHeader = headers.getHeaderString("Authorization");
        String token = authorizationHeader.substring("Bearer ".length());
        if (token.equals(userCloseDto.getToken())){
            boolean isClosed = userBusiness.closeUserAccount(userCloseDto);
            if (isClosed) {
                return Response.ok().build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();

    }

    /**
     * Gets all the user personal details
     * @param headers
     * @return
     */
    @GET
    @Path("/details")
    @Produces(MediaType.APPLICATION_JSON)
    public Response personalData(@Context HttpHeaders headers){
        String authorizationHeader = headers.getHeaderString("Authorization");
        String token = authorizationHeader.substring("Bearer ".length());
        try {
            FullUserDto user = userBusiness.getUserData(token);
            return Response.ok(user).build();
        } catch (SessionNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/modify")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modify(@Context HttpHeaders headers, FullUserDto fullUserDto) {
        String authorizationHeader = headers.getHeaderString("Authorization");
        String token = authorizationHeader.substring("Bearer ".length());
        boolean updated = userBusiness.updateUser(fullUserDto, token);
        if (updated) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

}
