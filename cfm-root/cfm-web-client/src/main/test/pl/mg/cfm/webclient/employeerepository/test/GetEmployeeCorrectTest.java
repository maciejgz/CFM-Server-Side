package pl.mg.cfm.webclient.employeerepository.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.mg.cfm.webclient.data.entity.Employee;
import pl.mg.cfm.webclient.data.repository.EmployeeRepository;

/**
 * Created by m on 2015-04-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/applicationContextPersistence.xml"})
public class GetEmployeeCorrectTest {

    @Autowired
    EmployeeRepository repository;

    @Before
    public void before(){

    }

    @Test
    public void test(){

        Employee employee = repository.getEmployee(1);
        System.out.println(employee.getFirstName());

    }
}
