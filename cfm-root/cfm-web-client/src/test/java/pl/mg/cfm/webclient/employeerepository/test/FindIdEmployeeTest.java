package pl.mg.cfm.webclient.employeerepository.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.mg.cfm.webclient.data.entity.Employee;
import pl.mg.cfm.webclient.data.repository.EmployeeCriteria;
import pl.mg.cfm.webclient.data.repository.EmployeeRepository;
import pl.mg.cfm.webclient.data.repository.SearchOperator;

import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Created by m on 2015-04-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/applicationContextPersistence.xml"})
public class FindIdEmployeeTest {

    @Autowired
    EmployeeRepository repository;

    @Before
    public void before() {
    }

    @Test
    public void equalsId() {
        EmployeeCriteria criteria = new EmployeeCriteria();
        criteria.setEmployeeId(1);
        criteria.setEmployeeIdOperator(SearchOperator.EQ.toString());


        List<Employee> employees = repository.findEmployee(criteria);
        for (Employee emp : employees) {
            System.out.println(emp);
        }

        assertEquals(employees.get(0).getIdemployee().intValue(), 1);
    }

    @Test
    public void greaterThanId() {
        EmployeeCriteria criteria = new EmployeeCriteria();
        criteria.setEmployeeId(5);
        criteria.setEmployeeIdOperator(SearchOperator.GTEQ.toString());


        List<Employee> employees = repository.findEmployee(criteria);

        System.out.println("GTEQ");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
}
