package pl.mg.cfm.ws;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("")
public class CfmBaseApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        return Collections.emptySet();
    }

}
