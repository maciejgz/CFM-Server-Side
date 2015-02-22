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
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("cfm-ejb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trx = null;
		try {
			trx = em.getTransaction();
			trx.begin();

			EmployeeRole userRole = new EmployeeRole();

			userRole.setId(8888);
			userRole.setName("test_role");
			// em.persist(userRole);
			// trx.commit();
			userRole = em.merge(userRole);
			em.flush();

			Employee userAdminSecond = new Employee();
			// userAdminSecond.setIdemployee(9996);
			userAdminSecond.setFirstName("Robert");
			userAdminSecond.setSecondName("Administracyjny");
			userAdminSecond.setRole(userRole);
			userAdminSecond.setPassword("testPass");
			em.persist(userAdminSecond);
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

	// @Test
	public void deleteEmployee() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("cfm-ejb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trx = null;
		try {
			trx = em.getTransaction();
			trx.begin();

			EmployeeRole userRole = new EmployeeRole();

			userRole.setId(8888);
			userRole.setName("test_role");
			userRole = em.merge(userRole);

			Employee userAdminFirst = new Employee();
			userAdminFirst.setIdemployee(9996);
			userAdminFirst.setFirstName("Robert");
			userAdminFirst.setSecondName("Administracyjny");
			userAdminFirst.setRole(userRole);
			userAdminFirst.setPassword("testPass");
			em.remove(em.contains(userAdminFirst) ? userAdminFirst : em
					.merge(userAdminFirst));
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
