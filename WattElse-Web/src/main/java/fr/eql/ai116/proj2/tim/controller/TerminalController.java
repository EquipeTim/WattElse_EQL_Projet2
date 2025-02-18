package fr.eql.ai116.proj2.tim.controller;

import fr.eql.ai116.proj2.tim.business.TerminalBusiness;
import fr.eql.ai116.proj2.tim.entity.dto.ChargingStationDto;
import fr.eql.ai116.proj2.tim.entity.dto.SearchDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/terminals")
public class TerminalController {

    @EJB
    private TerminalBusiness terminalBusiness;

    @POST
    @Path("/find")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findTerminals(SearchDto searchDto) {
        List<ChargingStationDto> stations = terminalBusiness.findTerminals(searchDto);
        return Response.ok(stations).build();
    }

    @GET
    @Path("/info/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfo(@PathParam("id") Long terminalId) {
        ChargingStationDto station = terminalBusiness.findTerminalsById(terminalId);
        return Response.ok(station).build();
    }
}
