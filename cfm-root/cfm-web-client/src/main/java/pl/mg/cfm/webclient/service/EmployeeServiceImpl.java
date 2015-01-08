package pl.mg.cfm.webclient.service;

import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public boolean login(String user, String password) {
        return true;
    }

}
