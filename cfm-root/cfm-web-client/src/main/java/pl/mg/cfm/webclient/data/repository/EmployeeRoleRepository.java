package pl.mg.cfm.webclient.data.repository;

import pl.mg.cfm.webclient.data.entity.EmployeeRole;

import java.util.List;

/**
 * Created by m on 2015-04-28.
 */
public interface EmployeeRoleRepository {
    public EmployeeRole getRole(String roleName);

    public List<EmployeeRole> getAllRoles();
    
    public EmployeeRole addRole(int roleId, String name);
}
