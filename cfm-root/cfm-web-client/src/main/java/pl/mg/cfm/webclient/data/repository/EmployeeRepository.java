package pl.mg.cfm.webclient.data.repository;

import pl.mg.cfm.dao.exceptions.EmployeeNotFoundException;
import pl.mg.cfm.dao.exceptions.InvalidPasswordException;
import pl.mg.cfm.dao.exceptions.RegisterEmployeeException;
import pl.mg.cfm.domain.EmployeePojo;
import pl.mg.cfm.domain.EmployeeRolePojo;
import pl.mg.cfm.webclient.data.entity.Employee;

public interface EmployeeRepository {

    
    public boolean login(String id, String password) throws EmployeeNotFoundException, InvalidPasswordException;

    public Employee getEmployee(Integer id) throws EmployeeNotFoundException;
    
    public Integer registerEmployee(String firstName, String lastName,String password) throws RegisterEmployeeException;
    
    public void updateEmployee(Employee employee)throws EmployeeNotFoundException;



}
