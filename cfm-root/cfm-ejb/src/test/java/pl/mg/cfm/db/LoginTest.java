package pl.mg.cfm.db;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import pl.mg.cfm.commons.dao.CFMDao;
import pl.mg.cfm.dao.CFMEJBRepository;
import pl.mg.cfm.dao.exceptions.EmployeeNotFoundException;
import pl.mg.cfm.dao.exceptions.InvalidPasswordException;

public class LoginTest {

    @Test(expected = InvalidPasswordException.class)
    public void wrongLogin() throws UnsupportedOperationException, EmployeeNotFoundException, InvalidPasswordException {
        CFMDao dao = new CFMEJBRepository();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cfm-ejb");
        EntityManager em = emf.createEntityManager();
        dao.setEm(em);

        String username = "1";
        String password = "bad_pass";

        dao.login(username, password);

    }

    @Test(expected = EmployeeNotFoundException.class)
    public void employeeNotFound() throws UnsupportedOperationException, EmployeeNotFoundException,
            InvalidPasswordException {
        CFMDao dao = new CFMEJBRepository();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cfm-ejb");
        EntityManager em = emf.createEntityManager();
        dao.setEm(em);

        String username = "0";
        String password = "bad_pass";

        System.out.println(dao.login(username, password));

    }

    @Test
    public void correctLogin() {
        CFMDao dao = new CFMEJBRepository();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cfm-ejb");
        EntityManager em = emf.createEntityManager();
        dao.setEm(em);

        String username = "1";
        String password = "pass";

        try {
            assertEquals(true, dao.login(username, password));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
