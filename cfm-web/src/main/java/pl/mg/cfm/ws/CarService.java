package pl.mg.cfm.ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import pl.mg.cfm.commons.dao.CFMDao;

@Stateless
@Path("/car-service")
public class CarService {

    Logger logger = Logger.getLogger(CarService.class);

    @EJB
    private CFMDao dao;

    @GET
    @Path("/all")
    @javax.ws.rs.Produces("application/json")
    public Response getAllCars() {
        Response.ResponseBuilder rb = Response.ok(dao.getAllCars());
        return rb.build();
    }
}
