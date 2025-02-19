package fr.eql.ai116.proj2.tim.controller;

import fr.eql.ai116.proj2.tim.business.AuthorizationException;
import fr.eql.ai116.proj2.tim.business.CarBusiness;
import fr.eql.ai116.proj2.tim.entity.Car;
import fr.eql.ai116.proj2.tim.entity.PlugType;
import fr.eql.ai116.proj2.tim.entity.dto.CarDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless //classe SpaceController est un EJB (Enterprise JavaBean) sans état
@Path("/car") //le chemin d'accès pour cette classe est /car
public class CarController {

        @EJB
        CarBusiness carBusiness;

        @GET
        @Path("/get/all")
        @Produces(MediaType.APPLICATION_JSON)
        public Response fetchUserCars(@Context HttpHeaders headers) {
            String authorizationHeader = headers.getHeaderString("Authorization");
            String token = authorizationHeader.substring("Bearer ".length());
            if (token != null){
                List<Car> cars = carBusiness.findUserCar(token);
                return Response.ok(cars).build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        }


        @POST
        @Path("/add")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response addCar(CarDto carDto) {
            boolean success = carBusiness.addCar(carDto);
            if (success){
                return Response.ok().build();
            }
            return Response.status(Response.Status.FOUND).build();
        }


    @POST
    @Path("/modify")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modify(CarDto carDto) {
        boolean updated = carBusiness.modifyCar(carDto);
        if (updated) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }



}
