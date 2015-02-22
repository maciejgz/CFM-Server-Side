package pl.mg.cfm.ws;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.jboss.logging.Logger;

@ApplicationPath("/")
public class CfmBaseApplication extends Application {
    private static Logger logger = Logger.getLogger(CfmBaseApplication.class);

    @Override
    public Set<Class<?>> getClasses() {
        logger.debug("CfmBaseApplication");

        return Collections.EMPTY_SET;
    }
}
