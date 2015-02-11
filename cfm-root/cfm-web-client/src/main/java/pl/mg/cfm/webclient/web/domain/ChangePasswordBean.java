package pl.mg.cfm.webclient.web.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class ChangePasswordBean {
    private String currentPassword;
    private String newPassword;
    private String newPasswordConfirm;

    public ChangePasswordBean() {
        currentPassword = "";
        newPassword = "";
        newPasswordConfirm = "";
    }

    @Override
    public String toString() {
        return "ChangePasswordBean [actualPassword=" + currentPassword + ", newPassword=" + newPassword
                + ", newPasswordConfirm=" + newPasswordConfirm + "]";
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }
}
