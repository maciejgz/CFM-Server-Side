package pl.mg.cfm.ws;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import pl.mg.cfm.commons.dao.CFMDao;

@Path("/employee")
public class EmployeeService {

	private static final Logger LOGGER = Logger
			.getLogger(EmployeeService.class);

	@EJB(beanName = "CFMDaoHibernate")
	CFMDao dao;

	@POST
	@Path("/register")
	@Consumes("application/json")
	public Response registerUser(@PathParam("firstName") String firstName,
			@PathParam("lastName") String lastName,
			@PathParam("roleId") int roleId,
			@PathParam("password") String password) {
		LOGGER.debug("register_user;firstName=" + firstName + ",lastName="
				+ lastName + ";roleId=" + roleId + ";password=" + password);
		
		return null;
	}

}
