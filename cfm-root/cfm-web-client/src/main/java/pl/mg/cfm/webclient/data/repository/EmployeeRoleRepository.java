package pl.mg.cfm.webclient.data.repository;

import pl.mg.cfm.webclient.data.entity.EmployeeRole;

import java.util.List;

/**
 * Created by m on 2015-04-28.
 */
public interface EmployeeRoleRepository {
    EmployeeRole getRole(String roleName);

    List<EmployeeRole> getAllRoles();
}
