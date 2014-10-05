package pl.mg.cfm.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

import pl.mg.cfm.model.Employee;
import pl.mg.cfm.model.EmployeeRole;

public class EmployeeTests {

    @Test
    public void addEmployee() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cfm-ejb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = null;
        try {
            trx = em.getTransaction();
            trx.begin();

            EmployeeRole userRole = new EmployeeRole();

            userRole.setId(8888);
            userRole.setName("test_role");

            Employee userAdminFirst = new Employee();
            userAdminFirst.setIdemployee(9998);
            userAdminFirst.setFirstName("Robert");
            userAdminFirst.setSecondName("Administracyjny");
            userAdminFirst.setRole(userRole);
            userAdminFirst.setPassword("testPass");
            em.persist(userAdminFirst);
            trx.commit();
        } catch (RuntimeException e) {
            if (trx != null && trx.isActive()) {
                trx.rollback();
            }
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }
    
    public void deleteEmployee(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cfm-ejb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = null;
        try {
            trx = em.getTransaction();
            trx.begin();

            EmployeeRole userRole = new EmployeeRole();

            userRole.setId(8888);
            userRole.setName("test_role");

            Employee userAdminFirst = new Employee();
            userAdminFirst.setIdemployee(9998);
            userAdminFirst.setFirstName("Robert");
            userAdminFirst.setSecondName("Administracyjny");
            userAdminFirst.setRole(userRole);
            userAdminFirst.setPassword("testPass");
            em.persist(userAdminFirst);
            trx.commit();
        } catch (RuntimeException e) {
            if (trx != null && trx.isActive()) {
                trx.rollback();
            }
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }
    
}
