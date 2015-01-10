package pl.mg.cfm.webclient.data.repository;

import pl.mg.cfm.dao.exceptions.InvalidPasswordException;
import pl.mg.cfm.dao.exceptions.UserNotFoundException;
import pl.mg.cfm.domain.EmployeePojo;

public interface EmployeeRepository {

    
    public boolean login(String id, String password) throws UserNotFoundException, InvalidPasswordException;

    EmployeePojo getEmployee(Integer id) throws UserNotFoundException;
    
}
