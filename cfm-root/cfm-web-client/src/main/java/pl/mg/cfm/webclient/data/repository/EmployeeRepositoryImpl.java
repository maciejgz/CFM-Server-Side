package pl.mg.cfm.webclient.data.repository;

import javax.persistence.*;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.mg.cfm.dao.exceptions.InvalidPasswordException;
import pl.mg.cfm.dao.exceptions.EmployeeNotFoundException;
import pl.mg.cfm.domain.EmployeePojo;
import pl.mg.cfm.webclient.data.entity.Employee;
import pl.mg.cfm.webclient.data.entity.EmployeeRole;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private Logger logger = Logger.getLogger(EmployeeRepositoryImpl.class);

    @PersistenceContext(name = "cfm-localhost")
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public boolean login(String id, String password) throws EmployeeNotFoundException, InvalidPasswordException {
        boolean result = false;

        try {
            Integer empId = Integer.parseInt(id);
            Employee employee = entityManager.find(Employee.class, empId);

            if (employee != null) {
                if (employee.getPassword().equals(password)) {
                    result = true;
                } else {
                    throw new InvalidPasswordException();
                }
            }
        } catch (NumberFormatException e) {
            throw new EmployeeNotFoundException("User " + id + " not found");
        } catch (EntityNotFoundException e) {
            throw new EmployeeNotFoundException("User " + id + " not found");
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getEmployee(Integer id) throws EmployeeNotFoundException {
        //made by sql query to make difference
        String sqlQuery = "select * from employee where idemployee like ?";
        Query query = entityManager.createNativeQuery(sqlQuery, Employee.class);
        query.setParameter(1, id);

        Employee employee = (Employee) query.getSingleResult();
        if (employee == null) {
            throw new EmployeeNotFoundException("User not found");
        }
        // logger.debug(employee.toString());
        EmployeePojo emPojo = new EmployeePojo();
        emPojo.setId(employee.getIdemployee());
        emPojo.setFirstName(employee.getFirstName());
        emPojo.setLastName(employee.getLastName());
        emPojo.setPassword(employee.getPassword());
        emPojo.setRoleName(employee.getRole().getName());
        return employee;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer registerEmployee(String firstName, String lastName, String password) {
        String sqlQuery = "select cfm.register_user(?,?,?)";
        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter(1, firstName);
        query.setParameter(2, lastName);
        query.setParameter(3, password);

        int id = (int) query.getSingleResult();

        logger.debug("registerEmployee repository. result=" + id);
        return id;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {EmployeeNotFoundException.class})
    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {

       Employee updatedEmployee = entityManager.find(Employee.class, employee.getIdemployee());
//        entityManager.getTransaction();
        updatedEmployee.setFirstName(employee.getFirstName());
        updatedEmployee.setLastName(employee.getLastName());
        updatedEmployee.setPassword(employee.getPassword());

        EmployeeRole role = null;
        if (employee.getRole().getName() != null) {
            String sqlQuery = "select role from employee_role role where role.name like ?";
            TypedQuery<EmployeeRole> query = entityManager.createQuery(sqlQuery, EmployeeRole.class)
                    .setParameter("name", employee.getRole().getName());
//            query.setParameter(1, employee.getRoleName());
            role = query.getSingleResult();
        }
        if (role != null) {
            updatedEmployee.setRole(role);
        }
        entityManager.merge(updatedEmployee);


        String sqlQuery = "update employee set first_name=?,last_name=?,password=? where idemployee=?";

        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter(1, employee.getFirstName());
        query.setParameter(2, employee.getLastName());
        query.setParameter(3, employee.getPassword());
        query.setParameter(4, employee.getIdemployee());
        logger.debug("query=" + query.toString());
        int rows = query.executeUpdate();
        logger.debug("updateUser; updated rows=" + rows);
    }

}
