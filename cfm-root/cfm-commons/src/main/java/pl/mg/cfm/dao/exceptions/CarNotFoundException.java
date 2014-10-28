package pl.mg.cfm.dao.exceptions;

public class CarNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public CarNotFoundException() {
        super();
    }

    public CarNotFoundException(String message) {
        super(message);
    }

    public CarNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CarNotFoundException(Throwable cause) {
        super(cause);
    }
}
