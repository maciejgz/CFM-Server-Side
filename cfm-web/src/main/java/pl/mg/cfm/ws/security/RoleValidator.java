package pl.mg.cfm.ws.security;

import java.util.HashMap;

import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import pl.mg.cfm.pojo.EmployeePojo;

@Stateless
public class RoleValidator {

    Logger logger = Logger.getLogger(RoleValidator.class);

    private HashMap<String, String> functions;

    public boolean validateRole(String functionName, String userRole) {
        logger.debug("validateRole=" + functionName + ";" + userRole);
        if (!this.functions.containsKey(functionName)) {
            logger.debug("Developer error: function security settings not defined.");
            return false;
        } else {
            String role = functions.get(functionName);
            if (role.equals(EmployeePojo.ROLE_ALL) || role.contains(userRole)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public HashMap<String, String> getFunctions() {
        return functions;
    }

    public void setFunctions(HashMap<String, String> functions) {
        this.functions = functions;
    }

}
