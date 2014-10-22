package pl.mg.cfm.ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import pl.mg.cfm.commons.dao.CFMDao;

@Stateless
@Path("/car")
public class CarService {

    Logger logger = Logger.getLogger(CarService.class);

    @EJB(beanName = "CFMDaoHibernate")
    private CFMDao dao;

    @GET
    @Path("/")
    @javax.ws.rs.Produces("application/json")
    public Response getAllCars() {
        logger.debug("CarService: getAllCars");
        Response.ResponseBuilder rb = Response.ok(dao.getAllCars());
        return rb.build();
    }

    @GET
    @Path("/{id}")
    @javax.ws.rs.Produces("application/json")
    public Response getCar(@PathParam("id") String id) {
        // TODO obsługa błędów zwracanych w JSON
        logger.debug("CarService: trying to find car with id=" + id);
        Response.ResponseBuilder rb = Response.ok(dao.getCar(id));
        return rb.build();
    }

    /*
     * @POST
     * 
     * @Path("/{id}")
     * 
     * @Consumes("application/json")
     * 
     * @Produces("application/json") public Response createCar
     */
}
