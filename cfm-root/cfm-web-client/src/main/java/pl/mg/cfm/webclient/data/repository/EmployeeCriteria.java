package pl.mg.cfm.webclient.data.repository;

/**
 * Employee search criteria
 * Created by m on 2015-03-04.
 */
public class EmployeeCriteria {

    private String firstName = null;
    private String firstNameOperator = null;
    private String lastName = null;
    private String lastNameOperator = null;

    private Integer employeeId;
    private String employeeIdOperator = null;

    public EmployeeCriteria() {
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

    public String getFirstNameOperator() {
        return firstNameOperator;
    }

    public void setFirstNameOperator(String firstNameOperator) {
        this.firstNameOperator = firstNameOperator;
    }

    public String getLastNameOperator() {
        return lastNameOperator;
    }

    public void setLastNameOperator(String lastNameOperator) {
        this.lastNameOperator = lastNameOperator;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeIdOperator() {
        return employeeIdOperator;
    }

    public void setEmployeeIdOperator(String employeeIdOperator) {
        this.employeeIdOperator = employeeIdOperator;
    }
}
