package pl.mg.cfm.db;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import pl.mg.cfm.commons.dao.CFMDao;
import pl.mg.cfm.dao.CFMDaoHibernate;

public class LoginTest {

    @Test
    public void wrongLogin() {
        CFMDao dao = new CFMDaoHibernate();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cfm-ejb");
        EntityManager em = emf.createEntityManager();
        dao.setEm(em);

        String username = "1";
        String password = "test_pass";

        try {
            assertEquals(false, dao.login(username, password));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void correctLogin() {
        CFMDao dao = new CFMDaoHibernate();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cfm-ejb");
        EntityManager em = emf.createEntityManager();
        dao.setEm(em);

        String username = "9996";
        String password = "testPass";

        try {
            assertEquals(true, dao.login(username, password));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
