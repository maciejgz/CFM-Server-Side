package pl.mg.cfm.ws.security;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.StringTokenizer;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.util.Base64;

import pl.mg.cfm.commons.dao.CFMDao;
import pl.mg.cfm.dao.exceptions.InvalidPasswordException;
import pl.mg.cfm.dao.exceptions.UserNotFoundException;

@Provider
public class SecurityInterceptor implements javax.ws.rs.container.ContainerRequestFilter {

    @EJB(beanName = "CFMDaoHibernate")
    private CFMDao dao;

    private Logger logger = Logger.getLogger(SecurityInterceptor.class);
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    private static final ServerResponse ACCESS_DENIED = new ServerResponse("Access denied for this resource", 401,
            new Headers<Object>());;
    private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse("Nobody can access this resource", 403,
            new Headers<Object>());;
    private static final ServerResponse SERVER_ERROR = new ServerResponse("INTERNAL SERVER ERROR", 500,
            new Headers<Object>());;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        logger.debug("preProvider preprocess");
        System.out.println("preProvider preprocess");
        ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) requestContext
                .getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
        Method method = methodInvoker.getMethod();

        final MultivaluedMap<String, String> headers = requestContext.getHeaders();

        final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

        final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

        if (authorization == null || authorization.isEmpty() || encodedUserPassword == null) {
            requestContext.abortWith(ACCESS_DENIED);
            return;
        }

        String usernameAndPassword;
        try {
            usernameAndPassword = new String(Base64.decode(encodedUserPassword));
            if (!checkLogin(usernameAndPassword.split(":"))) {
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }
            logger.debug("user and password: " + usernameAndPassword);
        } catch (IOException e) {
            requestContext.abortWith(ACCESS_DENIED);
            return;
        }

        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        System.out.println(username);
        System.out.println(password);

        logger.debug(username);
        logger.debug(password);
        return;
    }

    private boolean checkLogin(String[] split) {
        if (split == null || split.length != 2) {
            return false;
        }

        try {
            if (dao.login(split[0], split[1])) {
                return true;
            } else {
                return false;
            }
        } catch (UnsupportedOperationException | UserNotFoundException | InvalidPasswordException e) {
            return false;
        }

    }

}
