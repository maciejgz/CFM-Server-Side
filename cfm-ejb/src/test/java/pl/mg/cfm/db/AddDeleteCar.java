package pl.mg.cfm.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

import pl.mg.cfm.model.Car;
import pl.mg.cfm.model.Employee;
import pl.mg.cfm.model.EmployeeRole;

public class AddDeleteCar {

    @Test
    public void deleteCar() {

        AddDeleteCar obj = new AddDeleteCar();
        obj.addCar();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cfm-ejb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = null;
        try {
            trx = em.getTransaction();
            trx.begin();

            // userRole
            EmployeeRole userRole = new EmployeeRole();
            userRole.setId(8888);
            userRole.setName("test_role");
            userRole = em.merge(userRole);
            em.merge(userRole);

            // Employee
            Employee userAdminFirst = new Employee();
            userAdminFirst.setIdemployee(9994);
            userAdminFirst.setFirstName("Robert");
            userAdminFirst.setSecondName("Administracyjny");
            userAdminFirst.setRole(userRole);
            userAdminFirst.setPassword("testPass");
            em.merge(userAdminFirst);

            // CAR
            Car carForTest = new Car();
            carForTest.setCar_id("wsc1235");
            carForTest.setDistance(0);
            carForTest.setLatitude(null);
            carForTest.setLongitude(null);
            carForTest.setOwner(userAdminFirst);
            em.merge(carForTest);

            em.remove(em.contains(carForTest) ? carForTest : em.merge(carForTest));

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

    public void addCar() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cfm-ejb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = null;
        try {
            trx = em.getTransaction();
            trx.begin();

            // userRole
            EmployeeRole userRole = new EmployeeRole();
            userRole.setId(8888);
            userRole.setName("test_role");
            userRole = em.merge(userRole);
            em.persist(userRole);

            // Employee
            Employee userAdminFirst = new Employee();
            userAdminFirst.setIdemployee(9994);
            userAdminFirst.setFirstName("Robert");
            userAdminFirst.setSecondName("Administracyjny");
            userAdminFirst.setRole(userRole);
            userAdminFirst.setPassword("testPass");
            em.persist(userAdminFirst);

            // CAR
            Car carForTest = new Car();
            carForTest.setCar_id("wsc1235");
            carForTest.setDistance(0);
            carForTest.setLatitude(null);
            carForTest.setLongitude(null);
            carForTest.setOwner(userAdminFirst);
            em.persist(carForTest);

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
