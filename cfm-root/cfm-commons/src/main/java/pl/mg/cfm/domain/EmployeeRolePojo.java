package pl.mg.cfm.domain;

import java.io.Serializable;

public class EmployeeRolePojo implements Serializable {

    private static final long serialVersionUID = -7701761715221798972L;
    //    public static final String ROLE_USER = "ROLE_USER";
    //    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    private Integer roleId;

    private Role roleName;

    public EmployeeRolePojo(Integer roleId, Role roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public EmployeeRolePojo() {
        this.roleId = null;
        this.roleName = null;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Role getRoleName() {
        return roleName;
    }

    public void setRoleName(Role roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "EmployeeRolePojo [roleId=" + roleId + ", roleName=" + roleName + "]";
    }

}
