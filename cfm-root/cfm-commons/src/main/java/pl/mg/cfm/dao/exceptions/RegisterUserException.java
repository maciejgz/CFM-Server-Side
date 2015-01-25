package pl.mg.cfm.dao.exceptions;

public class RegisterUserException extends Exception {

    private static final long serialVersionUID = 7711891717036584406L;

    public RegisterUserException() {
        super();
    }

    public RegisterUserException(String message) {
        super(message);
    }

    public RegisterUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegisterUserException(Throwable cause) {
        super(cause);
    }
}
