package pl.mg.cfm.webclient.employeerolerepo.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.mg.cfm.webclient.data.entity.EmployeeRole;
import pl.mg.cfm.webclient.data.repository.EmployeeRoleRepository;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by m on 2015-04-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/applicationContextPersistence.xml"})
public class GetAllRolesTest {

    @Autowired
    EmployeeRoleRepository repository;

    @Before
    public void before() {

    }

    @Test
    public void test() {
        List<EmployeeRole> roles = repository.getAllRoles();
        System.out.println(roles);
        assertNotNull(roles);
    }
}
