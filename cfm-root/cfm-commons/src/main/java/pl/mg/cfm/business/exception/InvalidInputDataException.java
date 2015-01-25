package pl.mg.cfm.business.exception;

public class InvalidInputDataException extends Exception {

    private static final long serialVersionUID = -7477494848317993635L;

    public InvalidInputDataException() {
        super();
    }

    public InvalidInputDataException(String message) {
        super(message);
    }

    public InvalidInputDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputDataException(Throwable cause) {
        super(cause);
    }
}
