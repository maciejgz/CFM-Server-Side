package pl.mg.cfm.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import pl.mg.cfm.model.EmployeeRole;

/**
 * Lokalne testy odwzorowania bazy danych na JPA
 * 
 * @author m
 *
 */
public class JPATest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cfm-ejb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = null;
        try {
            trx = em.getTransaction();

            trx.begin();

            EmployeeRole userRole = new EmployeeRole();
            userRole.setId(1);
            userRole.setName("user");
            em.persist(userRole);
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
