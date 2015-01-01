package pl.mg.cfm.db;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import pl.mg.cfm.commons.dao.CFMDao;
import pl.mg.cfm.dao.CFMEJBRepository;
import pl.mg.cfm.pojo.CarPojo;

/**
 * Test połączenia do bazy danych openshift po przekierowaniu portów.
 * rhc port-forward cfm
 * @author Maciej Gzik
 *
 */
public class CarListOpenshiftTest {

    @Test
    public void getAllCars() {

        CFMDao dao = new CFMEJBRepository();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("openshift_db");
        EntityManager em = emf.createEntityManager();
        dao.setEm(em);

        List<CarPojo> cars = dao.getAllCars();
        Iterator<CarPojo> it = cars.iterator();
        while (it.hasNext()) {
            System.out.println(((CarPojo) it.next()).toString());
        }
    }
}
