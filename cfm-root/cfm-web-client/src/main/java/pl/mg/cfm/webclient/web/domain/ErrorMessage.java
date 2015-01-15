package pl.mg.cfm.webclient.web.domain;

public class ErrorMessage {
    private int errorCode = 0;
    private String errorMessage = "";

    public ErrorMessage() {
        errorCode = 0;
        errorMessage = "";
    }

    @Override
    public String toString() {
        return "ErrorMessage [errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
