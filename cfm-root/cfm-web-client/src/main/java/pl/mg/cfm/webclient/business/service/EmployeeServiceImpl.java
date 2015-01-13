package pl.mg.cfm.webclient.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.mg.cfm.dao.exceptions.InvalidPasswordException;
import pl.mg.cfm.dao.exceptions.UserNotFoundException;
import pl.mg.cfm.webclient.data.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public boolean login(String id, String password) {
        try {
            return repository.login(id, password);
        } catch (UserNotFoundException | InvalidPasswordException e) {
            return false;
        }
    }
}
