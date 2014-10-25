package pl.mg.cfm.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

import pl.mg.cfm.model.Car;
import pl.mg.cfm.model.CarPK;
import pl.mg.cfm.model.Employee;
import pl.mg.cfm.model.EmployeeRole;

public class AddCarTest {

    @Test
    public void addCar() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cfm-ejb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = null;
        try {
            trx = em.getTransaction();
            trx.begin();

            //userRole
            EmployeeRole userRole = new EmployeeRole();
            userRole.setId(8888);
            userRole.setName("test_role");
            userRole = em.merge(userRole);
            em.merge(userRole);
            
            //Employee
            Employee userAdminFirst = new Employee();
            userAdminFirst.setIdemployee(9996);
            userAdminFirst.setFirstName("Robert");
            userAdminFirst.setSecondName("Administracyjny");
            userAdminFirst.setRole(userRole);
            userAdminFirst.setPassword("testPass");
            em.merge(userAdminFirst);
            
            //CAR
            Car carForTest = new Car();
            carForTest.setCarPk(new CarPK("wsc1234"));
            carForTest.setDistance(0);
            carForTest.setLatitude(-50.123456789);
            carForTest.setLongitude(50.123456789);
            carForTest.setOwner(userAdminFirst);
            em.merge(carForTest);
            
            
            trx.commit();
        } catch (RuntimeException e) {
            if (trx != null && trx.isActive()) {
                trx.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

}
