package pl.mg.cfm.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Test;

import pl.mg.cfm.model.EmployeeRole;

public class EmployeeRoleTest {

    /**
     * Dodanie obiektu przez EntityManager
     */
    //@Test
    public void createRole() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cfm-ejb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = null;
        try {
            trx = em.getTransaction();

            trx.begin();

            EmployeeRole userRole = new EmployeeRole();
            userRole.setId(9999);
            userRole.setName("admin");
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

    /**
     * Usuniecie obiketu poprzez EntityManager
     */
//    @Test
    public void deleteRole() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cfm-ejb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = null;
        try {
            trx = em.getTransaction();

            trx.begin();

            EmployeeRole userRole = new EmployeeRole();
            userRole.setId(9999);
            userRole.setName("admin");
            // entityManager moze operowac tylko na aktywnych dla niego
            // obiektach. Jezeli obiekt nie jest jeszcze ustawiony to trzeba to
            // zrobic poprzez merge i podmienić obiekt entity
            if (!em.contains(userRole)) {
                userRole = em.merge(userRole);
            }
            // em.remove(em.contains(userRole) ? userRole : em.merge(userRole));

            em.remove(userRole);
            em.flush();
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

    /**
     * usuwanie testowego obiektu poprzez standardowe zapytanie sql przy zachowaniu standardów JPA
     */
    @Test
    public void deleteRoleByQuery() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cfm-ejb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = null;

        try {
            trx = em.getTransaction();

            trx.begin();
            
            Query query = em.createNativeQuery("delete from employee_role where id = ?");
            query.setParameter(1, 9999);
            int result = query.executeUpdate();
            System.out.println("query result=" + result);
            em.flush();
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
