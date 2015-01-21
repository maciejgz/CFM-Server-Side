package pl.mg.cfm.webclient.web.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.mg.cfm.dao.exceptions.InvalidPasswordException;
import pl.mg.cfm.dao.exceptions.UserNotFoundException;
import pl.mg.cfm.webclient.business.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/applicationContextPersistence.xml",
        "file:src/main/webapp/WEB-INF/applicationContextWeb.xml" })
public class UserDashboardController {

    @Autowired
    EmployeeService employeeService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        try {
            System.out.println(employeeService.login("user", "password"));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidPasswordException e) {
            e.printStackTrace();
        }
        assertNotNull(employeeService);
    }

}
