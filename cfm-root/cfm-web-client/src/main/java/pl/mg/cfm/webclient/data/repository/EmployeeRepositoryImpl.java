package pl.mg.cfm.webclient.data.repository;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.mg.cfm.dao.exceptions.EmployeeNotFoundException;
import pl.mg.cfm.dao.exceptions.OperationNotAllowedException;
import pl.mg.cfm.webclient.data.entity.Employee;
import pl.mg.cfm.webclient.data.entity.EmployeeRole;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private Logger logger = Logger.getLogger(EmployeeRepositoryImpl.class);

    @PersistenceContext(name = "cfm-localhost")
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public boolean login(String id, String password) {
        boolean result = false;

        try {
            Integer empId = Integer.parseInt(id);
            Employee employee = entityManager.find(Employee.class, empId);

            if (employee != null) {
                if (employee.getPassword().equals(password)) {
                    result = true;
                } else {
                    result = false;
                }
            }
        } catch (NumberFormatException e) {
            result = false;
        } catch (EntityNotFoundException e) {
            result = false;
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Employee getEmployee(Integer id) {
        //made by sql query to make difference
       /* String sqlQuery = "select * from employee where idemployee like ?";
        Query query = entityManager.createNativeQuery(sqlQuery, Employee.class);
        query.setParameter(1, id);*/

        /*Employee employee = (Employee) query.getSingleResult();*/
        Employee employee = entityManager.find(Employee.class, id);
        // get jest wymagany do wstêpnego pobrania zaleznoœci
        employee.getCars();
        employee.getRole();
        return employee;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer registerEmployee(String firstName, String lastName, String password) {
        //sql version
        /*String sqlQuery = "select cfm.register_user(?,?,?)";
        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter(1, firstName);
        query.setParameter(2, lastName);
        query.setParameter(3, password);

        int id = (int) query.getSingleResult();*/

        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setPassword(password);

        EmployeeRole role = entityManager.find(EmployeeRole.class, 1);
        employee.setRole(role);

        entityManager.persist(employee);

        int id = employee.getIdemployee();
        logger.debug("registerEmployee repository. result=" + id);
        return id;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {EmployeeNotFoundException.class})
    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {

        //wersja JPA
        entityManager.merge(employee);

       /* Employee updatedEmployee = entityManager.find(Employee.class, employee.getIdemployee());
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
        entityManager.merge(updatedEmployee);*/


        // wersja SQL
       /* String sqlQuery = "update employee set first_name=?,last_name=?,password=? where idemployee=?";

        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter(1, employee.getFirstName());
        query.setParameter(2, employee.getLastName());
        query.setParameter(3, employee.getPassword());
        query.setParameter(4, employee.getIdemployee());
        logger.debug("query=" + query.toString());
        int rows = query.executeUpdate();
        logger.debug("updateUser; updated rows=" + rows);*/
    }

    @Override
    public List<Employee> findEmployee(EmployeeCriteria criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> emp = cq.from(Employee.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        //employeeId
        if (criteria.getEmployeeId() != null) {
            SearchOperator operator = SearchOperator.valueOf(criteria.getEmployeeIdOperator());
            switch (operator) {
                case EQ:
                    predicates.add(cb.equal(emp.<Integer>get("idemployee"), criteria.getEmployeeId()));
                    break;
                case GTEQ:
                    predicates.add(cb.greaterThanOrEqualTo(emp.<Integer>get("idemployee"), criteria.getEmployeeId()));
                    break;
                case GT:
                    predicates.add(cb.greaterThan(emp.<Integer>get("idemployee"), criteria.getEmployeeId()));
                    break;
                case LTEQ:
                    predicates.add(cb.lessThanOrEqualTo(emp.<Integer>get("idemployee"), criteria.getEmployeeId()));
                    break;
                case LT:
                    predicates.add(cb.lessThan(emp.<Integer>get("idemployee"), criteria.getEmployeeId()));
                    break;
                case NOTLIKE:
                    predicates.add(cb.notEqual(emp.<Integer>get("idemployee"), criteria.getEmployeeId()));
                    break;
                default:
                    logger.error("Operation=" + operator.toString() + " on employee id search is not allowed! Expression will be omitted.");
            }
        }

        //firstName
        if (criteria.getFirstName() != null && !criteria.getFirstName().equals("")) {
            SearchOperator operator = SearchOperator.valueOf(criteria.getFirstNameOperator());
            switch (operator) {
                case EQ:
                    predicates.add(cb.equal(emp.<String>get("firstName"), criteria.getFirstName()));
                    break;
                case LIKE:
                    predicates.add(cb.like(emp.<String>get("firstName"), criteria.getFirstName()));
                    break;
                case NOTLIKE:
                    predicates.add(cb.notLike(emp.<String>get("firstName"), criteria.getFirstName()));
                    break;
                default:
                    logger.error("Operation=" + operator.toString() + " on employee first name search is not allowed! Expression will be omitted.");
            }
        }

        //lastName
        if (criteria.getLastName() != null && !criteria.getLastName().equals("")) {
            SearchOperator operator = SearchOperator.valueOf(criteria.getLastNameOperator());
            switch (operator) {
                case EQ:
                    predicates.add(cb.equal(emp.<String>get("lastName"), criteria.getLastName()));
                    break;
                case LIKE:
                    predicates.add(cb.like(emp.<String>get("lastName"), criteria.getLastName()));
                    break;
                case NOTLIKE:
                    predicates.add(cb.notLike(emp.<String>get("lastName"), criteria.getLastName()));
                    break;
                default:
                    logger.error("Operation=" + operator.toString() + " on employee last name search is not allowed! Expression will be omitted.");
            }
        }
        cq.select(emp).where(predicates.toArray(new Predicate[]{}));
        TypedQuery<Employee> q = entityManager.createQuery(cq);
        return q.getResultList();
    }

}
