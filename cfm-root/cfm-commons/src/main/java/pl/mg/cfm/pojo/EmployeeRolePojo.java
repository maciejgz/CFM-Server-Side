package pl.mg.cfm.pojo;

public class EmployeeRolePojo {

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

    public String toString() {
        return "roleId=" + roleId + ";roleName=" + roleName;
    }

}
