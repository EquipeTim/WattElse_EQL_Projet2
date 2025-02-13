package fr.eql.ai116.proj2.tim.controller;

import fr.eql.ai116.proj2.tim.business.AuthenticationException;
import fr.eql.ai116.proj2.tim.business.UserBusiness;
import fr.eql.ai116.proj2.tim.entity.dto.FullUserDto;
import fr.eql.ai116.proj2.tim.entity.dto.UserCloseDto;
import fr.eql.ai116.proj2.tim.entity.dto.UserDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

    @POST
    @Path("/close")
    @Produces(MediaType.APPLICATION_JSON)
    public Response close(UserCloseDto userCloseDto) {
        System.out.println(userCloseDto.getUserId() + " " + userCloseDto.getToken());

        try {
            boolean isClosed = userBusiness.closeUserAccount(userCloseDto);
            if (isClosed) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
