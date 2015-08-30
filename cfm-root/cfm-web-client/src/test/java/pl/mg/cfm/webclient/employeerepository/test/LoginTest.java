package pl.mg.cfm.webclient.employeerepository.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.mg.cfm.dao.exceptions.InvalidPasswordException;
import pl.mg.cfm.dao.exceptions.EmployeeNotFoundException;
import pl.mg.cfm.webclient.data.repository.EmployeeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/applicationContextPersistence.xml" })
public class LoginTest {

    @Autowired
    EmployeeRepository repository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        if (repository == null) {
            System.out.println("repository is null");
        }
        String id = "1";
        String password = "newPass";

            System.out.println(repository.login(id, password));
            assertEquals(repository.login(id, password), true);

    }
}
