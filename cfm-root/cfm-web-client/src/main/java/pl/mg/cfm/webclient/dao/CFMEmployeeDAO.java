package pl.mg.cfm.webclient.dao;

import org.springframework.dao.DataAccessException;

import pl.mg.cfm.pojo.EmployeePojo;

public interface CFMEmployeeDAO {

    public EmployeePojo getEmployeeById(String id) throws DataAccessException;

    public void createEmployee(EmployeePojo employee) throws DataAccessException;

    public void deleteEmployeer(EmployeePojo employee) throws DataAccessException;

    public void updateEmployee(EmployeePojo employee) throws DataAccessException;
}
