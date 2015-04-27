package pl.mg.cfm.webclient.business.service;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import pl.mg.cfm.business.exception.InvalidInputDataException;
import pl.mg.cfm.dao.exceptions.InvalidPasswordException;
import pl.mg.cfm.dao.exceptions.RegisterEmployeeException;
import pl.mg.cfm.dao.exceptions.EmployeeNotFoundException;
import pl.mg.cfm.domain.EmployeePojo;
import pl.mg.cfm.webclient.business.validator.EmployeeValidator;
import pl.mg.cfm.webclient.data.adapter.EmployeeAdapter;
import pl.mg.cfm.webclient.data.entity.Employee;
import pl.mg.cfm.webclient.data.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository repository;

    @Override
    public boolean login(String id, String password) throws EmployeeNotFoundException, InvalidPasswordException {
        return repository.login(id, password);
    }

    @Override
    public EmployeePojo getEmployee(String id) throws NumberFormatException, EmployeeNotFoundException {
        try {
            return EmployeeAdapter.fromEntity(repository.getEmployee(Integer.parseInt(id)));
        } catch (NumberFormatException e) {
            logger.error(e.getLocalizedMessage(), e);
            throw e;
        }
    }

    @Override
    public Integer registerEmployee(String firstName, String lastName, String password)
            throws InvalidInputDataException, RegisterEmployeeException {
        logger.debug("registerEmployee service");
        if (!EmployeeValidator.validatePassword(password) || !EmployeeValidator.validateFirstName(firstName)
                || !EmployeeValidator.validateLastName(lastName)) {
            throw new InvalidInputDataException();
        }
        return repository.registerEmployee(firstName, lastName, password);
    }

    @Override
    public void updateEmployee(Integer id, String newFirstName, String newLastName, String newPassword)
            throws EmployeeNotFoundException, InvalidInputDataException {
        if (!EmployeeValidator.validateId(id.toString()) || !EmployeeValidator.validateFirstName(newFirstName)
                || !EmployeeValidator.validateLastName(newLastName) || !EmployeeValidator.validatePassword(newPassword)) {
            logger.error("ErrorDuring validation input data during employee update. id=" + id + ",firstName="
                    + newFirstName + ",lastName=" + newLastName + ",password=" + newPassword);
            throw new InvalidInputDataException();
        }
        EmployeePojo oldEmployeePojo = EmployeeAdapter.fromEntity(repository.getEmployee(id));

        if (!oldEmployeePojo.getFirstName().equals(newFirstName)) {
            oldEmployeePojo.setFirstName(newFirstName);
        }

        if (!oldEmployeePojo.getLastName().equals(newLastName)) {
            oldEmployeePojo.setLastName(newLastName);
        }

        if (!oldEmployeePojo.getPassword().equals(newPassword)) {
            oldEmployeePojo.setPassword(newPassword);
        }
        //        logger.debug("updating employee-" + oldEmployeePojo.toString());

        repository.updateEmployee(EmployeeAdapter.toEntity(oldEmployeePojo));

    }

    @Override
    public void updatePassword(EmployeePojo employeePojo, String newPassword, String newPasswordConfirm)
            throws InvalidInputDataException, EmployeeNotFoundException {
        logger.debug("editPass - employeePojo=" + employeePojo);
        logger.debug("editPass - newPassword=" + newPassword);
        logger.debug("editPass - newPasswordConfirm=" + newPasswordConfirm);


        if (employeePojo == null || !newPassword.equals(newPasswordConfirm) || !EmployeeValidator.validatePassword(newPassword)
                || !EmployeeValidator.validatePassword(newPasswordConfirm)) {
            throw new InvalidInputDataException();
        }
        employeePojo.setPassword(newPassword);
        this.repository.updateEmployee(EmployeeAdapter.toEntity(employeePojo));
    }
}
