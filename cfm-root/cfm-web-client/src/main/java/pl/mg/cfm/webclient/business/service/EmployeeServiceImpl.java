package pl.mg.cfm.webclient.business.service;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.mg.cfm.business.exception.InvalidInputDataException;
import pl.mg.cfm.dao.exceptions.InvalidPasswordException;
import pl.mg.cfm.dao.exceptions.RegisterUserException;
import pl.mg.cfm.dao.exceptions.UserNotFoundException;
import pl.mg.cfm.domain.EmployeePojo;
import pl.mg.cfm.webclient.business.validator.Validator;
import pl.mg.cfm.webclient.data.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository repository;

    @Override
    public boolean login(String id, String password) throws UserNotFoundException, InvalidPasswordException {
        return repository.login(id, password);
    }

    @Override
    public EmployeePojo getEmployee(String id) throws NumberFormatException, UserNotFoundException {
        try {
            return repository.getEmployee(Integer.parseInt(id));
        } catch (NumberFormatException | UserNotFoundException e) {
            logger.error(e.getLocalizedMessage(), e);
            throw e;
        }
    }

    @Override
    public Integer registerEmployee(String firstName, String lastName, String password)
            throws InvalidInputDataException, RegisterUserException {
        logger.debug("registerEmployee service");
        if (!Validator.validatePassword(password) || !Validator.validateFirstName(firstName)
                || !Validator.validateLastName(lastName)) {
            throw new InvalidInputDataException();
        }
        return repository.register(firstName, lastName, password);
    }
}
