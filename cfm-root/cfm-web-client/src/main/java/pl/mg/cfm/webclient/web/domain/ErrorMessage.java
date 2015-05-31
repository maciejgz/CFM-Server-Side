package pl.mg.cfm.webclient.web.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Error message backed bean.
 */
@Component
@Scope("session")
public class ErrorMessage {
    private int errorCode = 0;
    private String errorMessage = "";

    public ErrorMessage() {
        errorCode = 0;
        errorMessage = "";
    }

    public ErrorMessage(int code, String message) {
        errorCode = code;
        errorMessage = message;
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
