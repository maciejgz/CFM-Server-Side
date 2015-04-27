package pl.mg.cfm.webclient.data.repository;

import pl.mg.cfm.dao.exceptions.EmployeeNotFoundException;
import pl.mg.cfm.dao.exceptions.InvalidPasswordException;
import pl.mg.cfm.dao.exceptions.RegisterEmployeeException;
import pl.mg.cfm.domain.EmployeePojo;
import pl.mg.cfm.domain.EmployeeRolePojo;
import pl.mg.cfm.webclient.data.entity.Employee;

/**
 * Methods don't throw errors because it is not well fashioned to throw exceptions when business case happen.
 */
public interface EmployeeRepository {

    
    public boolean login(String id, String password);

    public Employee getEmployee(Integer id);
    
    public Integer registerEmployee(String firstName, String lastName,String password) throws RegisterEmployeeException;

    public void updateEmployee(Employee employee)throws EmployeeNotFoundException;

}
