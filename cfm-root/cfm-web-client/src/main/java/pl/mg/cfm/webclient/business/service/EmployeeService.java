package pl.mg.cfm.webclient.business.service;

import pl.mg.cfm.business.exception.InvalidInputDataException;
import pl.mg.cfm.dao.exceptions.InvalidPasswordException;
import pl.mg.cfm.dao.exceptions.RegisterEmployeeException;
import pl.mg.cfm.dao.exceptions.EmployeeNotFoundException;
import pl.mg.cfm.domain.EmployeePojo;

/**
 * Interfejs serwisu operacji wykonywanych na uzytkowniku. Dzieki temu
 * oddzielamy warstwe aplikacji od bazy danych, ale jest to w pewnym sensie
 * skopiowanie funkcjonalnosci repository (nie do koñca, ale jednak).
 *
 * Service operuje na obiektach biznesowych POJO.
 * 
 * @author Maciej Gzik
 *
 */
public interface EmployeeService {

    public boolean login(String id, String password) throws EmployeeNotFoundException, InvalidPasswordException;

    public EmployeePojo getEmployee(String id) throws NumberFormatException, EmployeeNotFoundException;

    public void updateEmployee(Integer id, String newFirstName, String newLastName, String newPassword)
            throws EmployeeNotFoundException, InvalidInputDataException;

    public Integer registerEmployee(String firstName, String lastName, String password)
            throws InvalidInputDataException, RegisterEmployeeException;

    public void updatePassword(EmployeePojo employeePojo, String newPassword, String newPasswordConfirm)
            throws InvalidInputDataException, EmployeeNotFoundException;

}
