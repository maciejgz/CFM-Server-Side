package pl.mg.cfm.ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import pl.mg.cfm.commons.dao.CFMDao;
import pl.mg.cfm.pojo.CarPojo;

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

    /****** ID ********/
    @GET
    @Path("/{carId}")
    @javax.ws.rs.Produces("application/json")
    public Response getCar(@PathParam("carId") String carId) {
        // TODO obsługa błędów zwracanych w JSON
        logger.debug("CarService: trying to find car with id=" + carId);
        Response.ResponseBuilder rb = Response.ok(dao.getCar(carId));
        return rb.build();
    }

    @PUT
    @Path("/{carId}")
    @javax.ws.rs.Produces("application/json")
    public Response updateCar(@PathParam("carId") String carId) {
        return null;
    }

    @POST
    @Path("/{carId}")
    @javax.ws.rs.Produces("application/json")
    public Response createCar(@PathParam("carId") String id, CarPojo car) {
        logger.debug("updateCar=" + car.toString());
        logger.debug("carId=" + id);
                
        return null;
    }

    @DELETE
    @Path("/{carId}")
    @javax.ws.rs.Produces("application/json")
    public Response deleteCar(@PathParam("carId") String carId) {
        return null;
    }

    /**** ID USER ***/
    @GET
    @Path("/{carId}/employee/{employeeId}")
    @javax.ws.rs.Produces("application/json")
    public Response getCarOwner(@PathParam("carId") String carId, @PathParam("employeeId") String userId) {
        return null;
    }

    @PUT
    @Path("/{carId}/employee/{employeeId}")
    @javax.ws.rs.Produces("application/json")
    public Response updateCarOwner(@PathParam("carId") String carId, @PathParam("employeeId") String userId) {
        return null;
    }

    @DELETE
    @Path("/{carId}/employee/{employeeId}")
    @javax.ws.rs.Produces("application/json")
    public Response deleteCarOwner(@PathParam("carId") String carId, @PathParam("employeeId") String userId) {
        return null;
    }

    /*** LOCATION ******/

    /**
     * Lista samochodów w poblizu samochodu którego id przekazujemy
     * 
     * @param id
     * @param userId
     * @param latitude
     * @param longitude
     * @return
     */
    @GET
    @Path("/{carId}/location")
    @javax.ws.rs.Produces("application/json")
    public Response getNearestCars(@PathParam("carId") String carId, @QueryParam("latitude") String latitude,
            @QueryParam("longitude") String longitude) {
        return null;
    }

    /**
     * Aktualizacja lokalizacji samochodu
     * 
     * @param carId
     * @param latitude
     * @param longitude
     * @return
     */
    @PUT
    @Path("/{carId}/location")
    @javax.ws.rs.Produces("application/json")
    public Response updateCarPosition(@PathParam("carId") String carId, @QueryParam("latitude") String latitude,
            @QueryParam("longitude") String longitude) {
        return null;
    }

    /**
     * Czyszczenie lokalizacji samochodu
     * 
     * @param carId
     * @param latitude
     * @param longitude
     * @return
     */
    @DELETE
    @Path("/{carId}/location")
    @javax.ws.rs.Produces("application/json")
    public Response clearCarPosition(@PathParam("carId") String carId, @QueryParam("latitude") String latitude,
            @QueryParam("longitude") String longitude) {
        return null;
    }

    /****** DISTANCE ******/
    @GET
    @Path("/{carId}/distance")
    @javax.ws.rs.Produces("application/json")
    public Response getCarDistance(@PathParam("carId") String carId, @QueryParam("newValue") String newValue) {
        return null;
    }

    @POST
    @Path("/{carId}/distance")
    @javax.ws.rs.Produces("application/json")
    public Response setCarDistance(@PathParam("carId") String carId, @QueryParam("newValue") String newValue) {
        return null;
    }

}
