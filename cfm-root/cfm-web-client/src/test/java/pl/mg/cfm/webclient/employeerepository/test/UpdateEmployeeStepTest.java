package pl.mg.cfm.webclient.employeerepository.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.mg.cfm.dao.exceptions.EmployeeNotFoundException;
import pl.mg.cfm.domain.EmployeePojo;
import pl.mg.cfm.webclient.data.adapter.EmployeeAdapter;
import pl.mg.cfm.webclient.data.entity.Employee;
import pl.mg.cfm.webclient.data.repository.EmployeeRepository;

/**
 * Created by m on 2015-03-28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/applicationContextPersistence.xml"})
public class UpdateEmployeeStepTest {


    @Autowired
    EmployeeRepository repository;

    @Autowired
    private EmployeeAdapter adapter;

    @Before
    public void before() {

    }

    @Test
    public void update() {


        Employee empForUpdate = repository.getEmployee(2);
        empForUpdate.setFirstName("nowe4");
        try {
            repository.updateEmployee(empForUpdate);
        } catch (EmployeeNotFoundException e) {
            e.printStackTrace();
        }

        empForUpdate.setLastName("last4");
        try {
            repository.updateEmployee(empForUpdate);
        } catch (EmployeeNotFoundException e) {
            e.printStackTrace();
        }
    }
}
