package ru.neoflex.courses14.rest;

import ru.neoflex.courses14.beans.EntityServiceLocal;
import ru.neoflex.courses14.jpaEntity.Airplane;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/airplanes")
public class AirplanesRestService {
    @EJB
    private EntityServiceLocal entityService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Airplane> getAirplanes(){
        return entityService.getAllAirplane();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addAirplane(Airplane airplane){
        entityService.saveAirplane(airplane);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateAirplane(Airplane airplane){
        entityService.saveAirplane(airplane);
    }

    @DELETE
    @Path("airplanes/{id}")
    public void deleteAirplane(@PathParam("id")String id){
        entityService.removeAirplane(id);
    }
}
