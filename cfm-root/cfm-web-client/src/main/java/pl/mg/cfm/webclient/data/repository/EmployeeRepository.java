package pl.mg.cfm.webclient.data.repository;

import java.util.List;

import pl.mg.cfm.dao.exceptions.InvalidPasswordException;
import pl.mg.cfm.dao.exceptions.RegisterEmployeeException;
import pl.mg.cfm.dao.exceptions.EmployeeNotFoundException;
import pl.mg.cfm.domain.EmployeePojo;

public interface EmployeeRepository {

    
    public boolean login(String id, String password) throws EmployeeNotFoundException, InvalidPasswordException;

    public EmployeePojo getEmployee(Integer id) throws EmployeeNotFoundException;
    
    public Integer register(String firstName, String lastName,String password) throws RegisterEmployeeException;
    
    public void updateEmployee(EmployeePojo employee);
    
    
    
}
