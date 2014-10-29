package pl.mg.cfm.dao.exceptions;

public class OperationNotAllowedException extends Exception {
    private static final long serialVersionUID = 1L;

    public OperationNotAllowedException() {
        super();
    }

    public OperationNotAllowedException(String message) {
        super(message);
    }

    public OperationNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationNotAllowedException(Throwable cause) {
        super(cause);
    }

}
