package pl.mg.cfm.pojo;

import java.io.Serializable;

public class EmployeeRolePojo implements Serializable {


    public static final String ROLE_ALL = "ALL";
    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";
	
    private static final long serialVersionUID = 1L;
    private Integer roleId;
    private String roleName;

    public EmployeeRolePojo(Integer roleId, String roleName) {
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "EmployeeRolePojo [roleId=" + roleId + ", roleName=" + roleName + "]";
    }

}
