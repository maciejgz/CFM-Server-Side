package pl.mg.cfm.webclient.business.service;

import java.util.List;

import pl.mg.cfm.business.exception.InvalidInputDataException;
import pl.mg.cfm.dao.exceptions.EmployeeNotFoundException;
import pl.mg.cfm.domain.CarPojo;

public interface CarService {

    public List<CarPojo> getEmployeeCars(Integer employeeId) throws EmployeeNotFoundException,
            InvalidInputDataException;
}
