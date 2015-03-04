package pl.mg.cfm.webclient.data.repository;

/**
 * Employee search criteria
 * Created by m on 2015-03-04.
 */
public class EmployeeCriteria {

    private String firstName = null;
    private String lastName = null;

    private String carId = null;


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

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }
}
