package pl.mg.cfm.dao.exceptions;

public class ObjectAlreadyExists extends Exception {
    private static final long serialVersionUID = 1L;

    public ObjectAlreadyExists() {
        super();
    }

    public ObjectAlreadyExists(String message) {
        super(message);
    }

    public ObjectAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectAlreadyExists(Throwable cause) {
        super(cause);
    }
}
