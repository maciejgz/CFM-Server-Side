package pl.mg.cfm.webclient.data.adapter;

import org.springframework.stereotype.Component;
import pl.mg.cfm.domain.EmployeePojo;
import pl.mg.cfm.domain.Role;
import pl.mg.cfm.webclient.data.entity.Employee;
import pl.mg.cfm.webclient.data.entity.EmployeeRole;

/**
 * Created by m on 2015-03-30.
 */
@Component
public class EmployeeAdapter implements PojoAdapter<EmployeePojo, Employee> {

    @Override
    public EmployeePojo fromEntity(Employee employee) {
        if (employee == null) {
            return null;
        } else {
            EmployeePojo pojo = new EmployeePojo();
            pojo.setFirstName(employee.getFirstName());
            pojo.setLastName(employee.getLastName());
            pojo.setId(employee.getIdemployee());
            pojo.setPassword(employee.getPassword());
            if (employee.getRole() != null)
                pojo.setRoleName(employee.getRole().getName());
            else pojo.setRoleName(Role.ROLE_USER.toString());
            return pojo;
        }

    }

    /**
     * Function creates persistence object
     *
     * @param employee
     * @return
     */
    @Override
    public Employee toEntity(EmployeePojo employee) {
        if (employee == null) {
            return null;
        } else {
            Employee employeeEntity = new Employee();
            employeeEntity.setIdemployee(employee.getId());
            employeeEntity.setFirstName(employee.getFirstName());
            employeeEntity.setLastName(employee.getLastName());
            employeeEntity.setPassword(employee.getPassword());
            EmployeeRole role = new EmployeeRole();
            employeeEntity.setRole(role);
            return employeeEntity;
        }
    }

}
