package pl.mg.cfm.webclient.employeerepository.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.mg.cfm.dao.exceptions.RegisterEmployeeException;
import pl.mg.cfm.webclient.data.repository.EmployeeRepository;

import static org.junit.Assert.assertTrue;

/**
 * Created by m on 2015-04-27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/applicationContextPersistence.xml" })

public class RegisterEmployeeTest {
    @Autowired
    EmployeeRepository repository;

    @Before
    public void before(){

    }

    @Test
    public void test(){

        try {
            int id = repository.registerEmployee("t1", "lastName", "password");
            System.out.println(id);
            assertTrue(id!=0);
        } catch (RegisterEmployeeException e) {
            e.printStackTrace();
        }


    }
}
