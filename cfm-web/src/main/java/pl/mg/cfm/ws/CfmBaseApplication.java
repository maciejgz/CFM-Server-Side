package pl.mg.cfm.ws;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.jboss.logging.Logger;

import pl.mg.cfm.ws.security.SecurityInterceptor;

@ApplicationPath("/")
public class CfmBaseApplication extends Application {
	private static Logger logger = Logger.getLogger(CfmBaseApplication.class);

	@Override
	public Set<Class<?>> getClasses() {
		logger.debug("CfmBaseApplication");
		System.out.println("CfmBaseApplication");
		return new HashSet<Class<?>>(Arrays.asList(SecurityInterceptor.class));
	}
}
