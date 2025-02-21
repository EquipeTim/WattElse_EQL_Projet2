package fr.eql.ai116.proj2.tim.controller;

import fr.eql.ai116.proj2.tim.business.TerminalBusiness;
import fr.eql.ai116.proj2.tim.entity.ChargingStation;
import fr.eql.ai116.proj2.tim.entity.OpeningHour;
import fr.eql.ai116.proj2.tim.entity.Revenue;
import fr.eql.ai116.proj2.tim.entity.Unavailability;
import fr.eql.ai116.proj2.tim.entity.dto.SearchDto;

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
        List<ChargingStation> stations = terminalBusiness.findTerminals(searchDto);
        return Response.ok(stations).build();
    }

    @GET
    @Path("/info/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfo(@PathParam("id") Long terminalId) {
        ChargingStation station = terminalBusiness.findTerminalsById(terminalId);
        return Response.ok(station).build();
    }

    @POST
    @Path("/info/hours")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOpeningHours(SearchDto searchDto) {
        List<OpeningHour> hours = terminalBusiness.getOpeningHours(searchDto);
        return Response.ok(hours).build();
    }

    @POST
    @Path("/info/occupied")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getReservedSlots(SearchDto searchDto) {
        List<OpeningHour> slots = terminalBusiness.getReservedTimeSlots(searchDto);
        return Response.ok(slots).build();
    }

    @POST
    @Path("/info/day/hours")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getDayOpeningHours(SearchDto searchDto) {
        List<OpeningHour> hours = terminalBusiness.getSpecificDayOpeningHours(searchDto);
        return Response.ok(hours).build();
    }
    @GET
    @Path("/info/day/occupied/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUnavailability(@PathParam("id") Long terminalId) {
        List<Unavailability> unavailability = terminalBusiness.getUnavailableDays(terminalId);
        return Response.ok(unavailability).build();
    }

    @POST
    @Path("/info/day/available")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAvailableSlots(SearchDto searchDto) {
        List<OpeningHour> available = terminalBusiness.getAvailableTimeSlots(searchDto);
        return Response.ok(available).build();
    }

    @POST
    @Path("/info/revenue")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRevenues(@Context HttpHeaders headers, SearchDto searchDto) {
        String authorizationHeader = headers.getHeaderString("Authorization");
        String token = authorizationHeader.substring("Bearer ".length());
        List<Revenue> revenues = terminalBusiness.getUserRevenues(searchDto, token);
        return Response.ok(revenues).build();

    }

}
