package pl.mg.cfm.webclient.dao.test;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.mg.cfm.domain.EmployeePojo;
import pl.mg.cfm.webclient.dao.CFMEmployeeDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/applicationContextDb.xml" })
public class CreateEmployeeTest {

    @Inject
    CFMEmployeeDAO dao;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        if (dao == null) {
            System.out.println("dao is null");
        }
        EmployeePojo empl = new EmployeePojo();
        empl.setId(1000);
        empl.setFirstName("testUser");
        empl.setLastName("testLastName");
        empl.setPassword("pass");
        empl.setRoleName("ADMIN");

        dao.createEmployee(empl);
    }
}
