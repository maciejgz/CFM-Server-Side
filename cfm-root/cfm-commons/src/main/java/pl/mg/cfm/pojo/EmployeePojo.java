package pl.mg.cfm.pojo;

import java.io.Serializable;

public class EmployeePojo implements Serializable {


    private static final long serialVersionUID = 1L;
    private Integer id;
    private String firstName;
    private String lastName;
    private String roleName;

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
        StringBuffer buffer = new StringBuffer("id=" + this.id + ";firstName=" + this.firstName + ";lastName="
                + lastName + ";roleId=" + roleName);
        return buffer.toString();
    }

}
