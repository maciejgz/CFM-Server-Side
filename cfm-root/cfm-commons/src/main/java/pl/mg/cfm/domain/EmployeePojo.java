package pl.mg.cfm.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeePojo implements Serializable {

    private static final long serialVersionUID = 1701580749583817832L;

    @NotNull
    private Integer id;

    private String firstName;
    private String lastName;
    private String roleName;

    @NotNull
    @Size(min = 4)
    private String password;

    public EmployeePojo() {
        this.id = null;
        this.firstName = null;
        this.lastName = null;
        this.password = null;
    }

    public EmployeePojo(EmployeePojo oldEmployeePojo) {
        id = oldEmployeePojo.id;
        firstName = oldEmployeePojo.firstName;
        lastName = oldEmployeePojo.lastName;
        password = oldEmployeePojo.password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "EmployeePojo [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", roleName="
                + roleName + ", password=" + password + "]";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
