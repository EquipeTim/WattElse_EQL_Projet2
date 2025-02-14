package fr.eql.ai116.proj2.tim.controller;

import fr.eql.ai116.proj2.tim.business.ComponentsBusiness;
import fr.eql.ai116.proj2.tim.entity.Plug;
import fr.eql.ai116.proj2.tim.entity.dto.CarDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/components")
public class ComponentsController {

    @EJB
    private ComponentsBusiness componentsBusiness;

    // Endpoint pour récupérer tous les plugs
    @GET
    @Path("/plug_type")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlugs() {
        try {
            List<Plug> plug_type = componentsBusiness.getAllPlug();
            return Response.ok(plug_type).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de la récupération des plugs.")
                    .build();
        }
    }

    // Endpoint pour récupérer les plugs d'un modèle de voiture donné (CarDto)
    @GET
    @Path("/plug_type/byCar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPlugsByCar(CarDto carDto) {
        try {
            List<String> plugs = componentsBusiness.findPlug(carDto);
            return Response.ok(plugs).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de la récupération des plugs pour la voiture.")
                    .build();
        }
    }
}