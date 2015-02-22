package pl.mg.cfm.ws.security;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.util.Base64;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pl.mg.cfm.commons.dao.CFMDao;
import pl.mg.cfm.dao.exceptions.InvalidPasswordException;
import pl.mg.cfm.dao.exceptions.EmployeeNotFoundException;
import pl.mg.cfm.domain.EmployeePojo;

@Provider
public class SecurityInterceptor implements javax.ws.rs.container.ContainerRequestFilter {

    private CFMDao dao;
    private Logger logger = Logger.getLogger(SecurityInterceptor.class);
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    private static final ServerResponse ACCESS_DENIED = new ServerResponse("Access denied for this resource", 401,
            new Headers<Object>());;
    private static final ServerResponse ACCESS_DENIED_ROLE = new ServerResponse("Access denied for your role", 401,
            new Headers<Object>());;
    private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse("Nobody can access this resource", 403,
            new Headers<Object>());;
    private static final ServerResponse SERVER_ERROR = new ServerResponse("INTERNAL SERVER ERROR", 500,
            new Headers<Object>());;

    private static final ServerResponse DB_ERROR = new ServerResponse("DATABASE NOT AVAILABLE", 500,
            new Headers<Object>());;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        logger.debug("preProvider preprocess");
        ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) requestContext
                .getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
        Set<String> httpMethods = methodInvoker.getHttpMethods();
        Iterator<String> it = httpMethods.iterator();

        logger.debug("httpMethods");
        String httpMethodName = "";
        while (it.hasNext()) {
            httpMethodName = it.next();
        }

        Method method = methodInvoker.getMethod();
        logger.debug("methodName=" + method.getName());

        final MultivaluedMap<String, String> headers = requestContext.getHeaders();

        final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

        if (authorization == null || authorization.get(0) == null || authorization.get(0).length() == 0) {
            requestContext.abortWith(ACCESS_DENIED);
            return;
        }

        final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

        if (authorization == null || authorization.isEmpty() || encodedUserPassword == null) {
            requestContext.abortWith(ACCESS_DENIED);
            return;
        }

        // lookup
        this.dao = lookupForDao();
        if (dao == null) {
            requestContext.abortWith(DB_ERROR);
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

        // check roles
        if (method.isAnnotationPresent(PermitAll.class)) {
            logger.debug("permit all");
            return;
        }
        if (method.isAnnotationPresent(DenyAll.class)) {
            logger.debug("deny all");
            requestContext.abortWith(ACCESS_DENIED);
            return;
        }

        if (!isRoleAllowed(method, httpMethodName, usernameAndPassword.split(":")[0])) {
            logger.debug("role not allowed");
            requestContext.abortWith(ACCESS_DENIED);
            return;
        }
        return;
    }

    private boolean isRoleAllowed(Method method, String httpMethodName, String username) {

        ApplicationContext context = new ClassPathXmlApplicationContext("securityApplicationContext.xml");

        String userRole = null;

        // getUserRole
        try {
            userRole = dao.getUserRole(username);
        } catch (EmployeeNotFoundException e) {
            logger.error(e.getMessage(), e);
            return false;
        }

        RoleValidator validator = (RoleValidator) context.getBean("roleValidator");

        boolean isAllowed = validator.validateRole(method.getName(), userRole);
        logger.debug("validatorValue=" + isAllowed);
        return isAllowed;
    }

    private boolean checkRole(Set<String> rolesSet, Integer id) {
        logger.debug("check role for id=" + id);
        boolean isAllowed = false;
        EmployeePojo employee = null;
        try {
            employee = dao.getEmployee(id);
            logger.debug(employee.getId());
        } catch (EmployeeNotFoundException e) {
            logger.error(e.getMessage(), e);
            return isAllowed;
        }

        Iterator<String> it = rolesSet.iterator();
        while (it.hasNext()) {
            String role = it.next();
            logger.debug("role=" + role);
            if (role.equals(employee.getRoleName())) {
                logger.debug("role=" + role + ";userRole=" + employee.getRoleName());
                isAllowed = true;
            }
        }
        return isAllowed;
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
        } catch (UnsupportedOperationException | EmployeeNotFoundException | InvalidPasswordException e) {
            return false;
        }
    }

    private CFMDao lookupForDao() {
        CFMDao dao = null;
        try {
            Context ctx = new InitialContext();
            dao = (CFMDao) ctx.lookup("java:global/CFM/cfm-ejb-0.0.1/CFMEJBRepository!pl.mg.cfm.commons.dao.CFMDao");
            return dao;
        } catch (NamingException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

}
