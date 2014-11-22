package pl.mg.cfm.message;

import java.io.Serializable;

public class CFMJsonSimpleMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer errorCode = 0;
    private String errorMessage = null;
    private String data = "";

    public CFMJsonSimpleMessage() {
    }

    public CFMJsonSimpleMessage(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;

    }

    public CFMJsonSimpleMessage(Integer errorCode, String errorMessage, String data) {
        this(errorCode, errorMessage);
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        String result = "errorCode=" + errorCode + ";errorMessage=" + errorMessage + ";data=" + data;
        return result;
    }

}
