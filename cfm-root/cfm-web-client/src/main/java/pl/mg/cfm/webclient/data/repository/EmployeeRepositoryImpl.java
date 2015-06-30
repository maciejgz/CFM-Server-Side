package pl.mg.cfm.webclient.data.repository;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.mg.cfm.dao.exceptions.EmployeeNotFoundException;
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
        // get jest wymagany do wst�pnego pobrania zalezno�ci
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

        List<Predicate> criteriaList = new ArrayList<Predicate>();
        //ParameterExpression<String> p = cb.parameter(String.class, )

        if (criteria.getEmployeeId() != null) {
            SearchOperator operator = SearchOperator.valueOf(criteria.getEmployeeIdOperator());

            switch (operator) {
                case EQ:
                    cq.where(cb.equal(emp.get("idemployee"), criteria.getEmployeeId()));
                    break;
                case GTEQ:
                    Expression<String> idemployee = emp.get("idemployee");
                    Expression<String> idemployeeParam = cb.parameter(String.class);
                   /* Predicate eq1 = cb.gt
                    cq.where(eq1);*/
                    break;
                case GT:
                   /* cb.gt()
                    cq.where(cb.gt(emp.get("idemployee"), 12));*/
                    break;
                default:
                    break;
            }
        }
        cq.select(emp);
        TypedQuery<Employee> q = entityManager.createQuery(cq);
        return q.getResultList();
    }

}
