package ru.neoflex.courses14.rest;

import ru.neoflex.courses14.beans.EntityServiceLocal;
import ru.neoflex.courses14.jpaEntity.Airport;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/* Created with IntelliJ IDEA.
 * User: Руслан
 * Date: 12.01.15
 * Time: 18:12 
 */
@Path("/airports")
public class AirportsRestService {
    @EJB
    private EntityServiceLocal entityService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Airport> getAirports(){
        return entityService.getAllAirport();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addAirport(Airport airport){
        entityService.saveAirport(airport);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateAirport(Airport airport){
        entityService.saveAirport(airport);
    }

    @DELETE
    @Path("airports/{id}")
    public void deleteAirport(@PathParam("id")String id){
        entityService.removeAirport(id);
    }
}
