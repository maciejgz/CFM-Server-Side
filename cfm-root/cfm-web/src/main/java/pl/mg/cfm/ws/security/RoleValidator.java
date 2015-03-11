package pl.mg.cfm.ws.security;

import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import java.util.HashMap;

@Stateless
public class RoleValidator {

    Logger logger = Logger.getLogger(RoleValidator.class);

    private HashMap<String, String> functionPermits;
    private String allAllowed = "ALL";

    public boolean validateRole(String functionName, String userRole) {
        logger.debug("validateRole=" + functionName + ";" + userRole);
        if (!this.functionPermits.containsKey(functionName)) {
            logger.debug("Developer error: function security settings not defined.");
            return false;
        } else {
            String role = functionPermits.get(functionName);
            if (role.contains(userRole) || role.equals(allAllowed)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public HashMap<String, String> getFunctionPermits() {
        return functionPermits;
    }

    public void setFunctionPermits(HashMap<String, String> functionPermits) {
        this.functionPermits = functionPermits;
    }

    public void setAllAllowed(String allAllowed) {
        this.allAllowed = allAllowed;
    }
}
