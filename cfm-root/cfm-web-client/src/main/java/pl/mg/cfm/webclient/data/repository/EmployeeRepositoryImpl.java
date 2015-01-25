package pl.mg.cfm.webclient.data.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.mg.cfm.dao.exceptions.InvalidPasswordException;
import pl.mg.cfm.dao.exceptions.UserNotFoundException;
import pl.mg.cfm.domain.EmployeePojo;
import pl.mg.cfm.webclient.data.entity.Employee;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private Logger logger = Logger.getLogger(EmployeeRepositoryImpl.class);

    @PersistenceContext(name = "cfm-localhost")
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public boolean login(String id, String password) throws UserNotFoundException, InvalidPasswordException {
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
            throw new UserNotFoundException("User " + id + " not found");
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException("User " + id + " not found");
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeePojo getEmployee(Integer id) throws UserNotFoundException {
        String sqlQuery = "select * from employee where idemployee like ?";
        Query query = entityManager.createNativeQuery(sqlQuery, Employee.class);
        query.setParameter(1, id);

        Employee employee = (Employee) query.getSingleResult();
        if (employee == null) {
            throw new UserNotFoundException("User not found");
        }
        // logger.debug(employee.toString());
        EmployeePojo emPojo = new EmployeePojo();
        emPojo.setId(employee.getIdemployee());
        emPojo.setFirstName(employee.getFirstName());
        emPojo.setLastName(employee.getLastName());
        emPojo.setRoleName(employee.getRole().getName());
        return emPojo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer register(String firstName, String lastName, String password) {
        String sqlQuery = "select cfm.register_user(?,?,?)";
        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter(1, firstName);
        query.setParameter(2, lastName);
        query.setParameter(3, password);

        int id = (int) query.getSingleResult();

        logger.debug("registerEmployee repository. result=" + id);
        return id;
    }

}
