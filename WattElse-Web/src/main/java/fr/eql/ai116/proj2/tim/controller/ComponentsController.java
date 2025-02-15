package fr.eql.ai116.proj2.tim.controller;

import fr.eql.ai116.proj2.tim.business.ComponentsBusiness;
import fr.eql.ai116.proj2.tim.entity.Plug;
import fr.eql.ai116.proj2.tim.entity.PlugType;
import fr.eql.ai116.proj2.tim.entity.dto.CarDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/components")
public class ComponentsController {

    @EJB
    private ComponentsBusiness componentsBusiness;

    @GET
    @Path("/plugs/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlugs() {
        List<Plug> plug_type = componentsBusiness.getAllPlug();
        return Response.ok(plug_type).build();
    }

    @GET
    @Path("/brands")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarBrands() {
        List<String> brands = componentsBusiness.getCarBrands();
        return Response.ok(brands).build();
    }

    @GET
    @Path("/model/{brand}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModels(@PathParam("brand") String brand) {
        List<String> models = componentsBusiness.getCarModels(brand);
        return Response.ok(models).build();
    }

    @GET
    @Path("/accountCloseReasons")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountCloseReasons() {
        List<String> reasons = componentsBusiness.getAccountCloseReasons();
        return Response.ok(reasons).build();
    }

    @POST
    @Path("/plugs/by_car")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPlugsByCar(CarDto carDto) {
        List<Plug> plugs = componentsBusiness.findPlug(carDto);
        return Response.ok(plugs).build();
    }
}