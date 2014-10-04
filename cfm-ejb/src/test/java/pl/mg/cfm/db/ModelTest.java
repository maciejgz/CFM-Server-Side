package pl.mg.cfm.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pl.mg.cfm.dao.HibernateUtil;
import pl.mg.cfm.model.EmployeeRole;

public class ModelTest {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
 
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
 
        
        EmployeeRole userRole = new EmployeeRole();
        userRole.setId(1);
        userRole.setName("user");
        
        EmployeeRole adminRole = new EmployeeRole();
        adminRole.setId(2);
        adminRole.setName("admin");
        
        
        
        session.getTransaction().commit();
        session.close();
    }

}
