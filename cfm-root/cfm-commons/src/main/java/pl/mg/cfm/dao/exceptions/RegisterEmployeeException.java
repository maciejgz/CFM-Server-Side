package pl.mg.cfm.dao.exceptions;

public class RegisterEmployeeException extends Exception {

    private static final long serialVersionUID = 7711891717036584406L;

    public RegisterEmployeeException() {
        super();
    }

    public RegisterEmployeeException(String message) {
        super(message);
    }

    public RegisterEmployeeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegisterEmployeeException(Throwable cause) {
        super(cause);
    }
}
