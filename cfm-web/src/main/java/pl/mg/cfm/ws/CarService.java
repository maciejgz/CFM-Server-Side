package pl.mg.cfm.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.jboss.logging.Logger;

import pl.mg.cfm.commons.dao.CFMDao;
import pl.mg.cfm.pojo.CarPojo;

@Stateless
@Path("/car")
public class CarService {

    Logger logger = Logger.getLogger(CarService.class);

    @EJB
    private CFMDao dao;

    @GET
    @Path("/")
    @javax.ws.rs.Produces("application/json")
    public List<CarPojo> getAllCars() {
        if (dao == null) {
            logger.debug("DAO is empty");
        }
        List<CarPojo> cars = dao.getAllCars();
        return cars;
    }
}
