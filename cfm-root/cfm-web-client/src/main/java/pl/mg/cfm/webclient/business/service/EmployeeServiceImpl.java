package pl.mg.cfm.webclient.business.service;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.mg.cfm.business.exception.InvalidInputDataException;
import pl.mg.cfm.dao.exceptions.InvalidPasswordException;
import pl.mg.cfm.dao.exceptions.RegisterEmployeeException;
import pl.mg.cfm.dao.exceptions.EmployeeNotFoundException;
import pl.mg.cfm.domain.EmployeePojo;
import pl.mg.cfm.webclient.business.validator.Validator;
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
            return repository.getEmployee(Integer.parseInt(id));
        } catch (NumberFormatException | EmployeeNotFoundException e) {
            logger.error(e.getLocalizedMessage(), e);
            throw e;
        }
    }

    @Override
    public Integer registerEmployee(String firstName, String lastName, String password)
            throws InvalidInputDataException, RegisterEmployeeException {
        logger.debug("registerEmployee service");
        if (!Validator.validatePassword(password) || !Validator.validateFirstName(firstName)
                || !Validator.validateLastName(lastName)) {
            throw new InvalidInputDataException();
        }
        return repository.register(firstName, lastName, password);
    }

    @Override
    public void updateEmployee(Integer id, String newFirstName, String newLastName, String newPassword)
            throws EmployeeNotFoundException, InvalidInputDataException {
        if (!Validator.validateId(id.toString()) || !Validator.validateFirstName(newFirstName)
                || !Validator.validateLastName(newLastName) || !Validator.validatePassword(newPassword)) {
            logger.error("ErrorDuring validation input data during employee update. id=" + id + ",firstName="
                    + newFirstName + ",lastName=" + newLastName + ",password=" + newPassword);
            throw new InvalidInputDataException();
        }
        EmployeePojo oldEmployeePojo = repository.getEmployee(id);

        if (!oldEmployeePojo.getFirstName().equals(newFirstName)) {
            oldEmployeePojo.setFirstName(newFirstName);
        }

        if (!oldEmployeePojo.getLastName().equals(newLastName)) {
            oldEmployeePojo.setLastName(newLastName);
        }

        if (!oldEmployeePojo.getPassword().equals(newPassword)) {
            oldEmployeePojo.setPassword(newPassword);
        }

        repository.updateEmployee(oldEmployeePojo);

    }
}
