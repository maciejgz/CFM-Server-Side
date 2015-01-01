package pl.mg.cfm.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import pl.mg.cfm.commons.dao.CFMDao;
import pl.mg.cfm.message.CFMJsonSimpleMessage;
import pl.mg.cfm.pojo.EmployeePojo;
import pl.mg.cfm.serializer.EmployeeSerializer;

@Stateless
@Path("/employee")
public class EmployeeService {

    private static final Logger logger = Logger.getLogger(EmployeeService.class);

    @EJB(beanName = "CFMEJBRepository")
    private CFMDao dao;

    @POST
    @Path("/register")
    @Consumes("application/json")
    public Response registerUser(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName,
            @PathParam("roleId") int roleId, @PathParam("password") String password) {
        logger.debug("register_user;firstName=" + firstName + ",lastName=" + lastName + ";roleId=" + roleId
                + ";password=" + password);
        return null;
    }

    @GET
    @Path("/")
    @javax.ws.rs.Produces("application/json")
    public Response getAllEmployees() {
        logger.debug("EmployeeService: getAllEmployees");
        EmployeeSerializer serializer = new EmployeeSerializer();

        Integer errorCode = 0;
        String errorMessage = null;
        List<EmployeePojo> employees = null;

        try {
            // employees = dao.get;
        } catch (Exception e) {
            errorCode = 1;
            errorMessage = e.getLocalizedMessage();
        }

        CFMJsonSimpleMessage message = new CFMJsonSimpleMessage();
        message.setErrorCode(errorCode);
        message.setErrorMessage(errorMessage);
        message.setData(serializer.serialize(employees));

        Response.ResponseBuilder rb = Response.ok(message);

        return rb.build();
    }

    /****** ID ********/
    @GET
    @Path("/{employeeId}")
    @javax.ws.rs.Produces("application/json")
    public Response getEmployee(@PathParam("employeeId") String employeeId) {
        logger.debug("EmployeeService: trying to find employee with id=" + employeeId);
        Integer errorCode = 0;
        String errorMessage = null;
        EmployeePojo employee = null;
        try {
            employee = dao.getEmployee(Integer.parseInt(employeeId));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            errorCode = 1;
            errorMessage = e.getLocalizedMessage();
        }

        EmployeeSerializer serializer = new EmployeeSerializer();
        CFMJsonSimpleMessage message = new CFMJsonSimpleMessage(errorCode, errorMessage, serializer.serialize(employee));
        return Response.ok(message).build();
    }

}
