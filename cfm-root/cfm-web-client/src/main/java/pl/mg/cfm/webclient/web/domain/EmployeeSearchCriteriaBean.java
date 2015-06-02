package pl.mg.cfm.webclient.web.domain;

/**
 * Backed bean for user searching form
 * Created by m on 2015-05-31.
 */
public class EmployeeSearchCriteriaBean {

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
}
