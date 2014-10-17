package pl.mg.cfm.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import pl.mg.cfm.commons.dao.CFMDao;
import pl.mg.cfm.pojo.CarPojo;

@Path("/car")
public class CarService {

    @EJB(beanName = "CFMDaoHibernate")
    CFMDao dao;
    
    @GET
    @Path("/all")
    public Response getAllCars() {
        List<CarPojo> cars = dao.getAllCars();
        
        
        
        return null;
    }

}
