package pl.mg.cfm.webclient.dao.test;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.mg.cfm.pojo.EmployeePojo;
import pl.mg.cfm.webclient.dao.CFMEmployeeDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml", "classpath:applicationContextDb.xml" })
public class UpdateEmployeeTest {

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
        empl.setFirstName("newEmplName");
        empl.setLastName("testLastName");
        empl.setPassword("pass");
        empl.setRoleName("ADMIN");

        dao.updateEmployee(empl);
    }
}
