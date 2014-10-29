package pl.mg.cfm.message;

import java.io.Serializable;

public class CFMJsonSimplyMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer errorCode = 0;
    private String errorMessage = null;
    private Object data = null;

    public CFMJsonSimplyMessage() {

    }

    public CFMJsonSimplyMessage(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;

    }

    public CFMJsonSimplyMessage(Integer errorCode, String errorMessage, Object data) {
        this(errorCode, errorMessage);
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
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
        String result = "errorCode=" + errorCode + ";errorMessage=" + errorMessage + ";data=";
        if (data == null) {
            result += "null";
        } else {
            result += data.toString();
        }
        return result;
    }

}
