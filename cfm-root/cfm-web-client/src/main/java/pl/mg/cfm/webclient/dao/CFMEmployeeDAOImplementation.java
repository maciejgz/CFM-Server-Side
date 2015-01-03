package pl.mg.cfm.webclient.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import pl.mg.cfm.domain.EmployeePojo;

public class CFMEmployeeDAOImplementation extends JdbcDaoSupport implements CFMEmployeeDAO {

    public static final String EMPLOYEE_ID_COL = "employee.idemployee";
    public static final String EMPLOYEE_FIRST_NAME_COL = "employee.first_name";
    public static final String EMPLOYEE_LAST_NAME_COL = "employee.last_name";
    public static final String EMPLOYEE_ROLE_ID_COL = "employee.role_id";
    public static final String EMPLOYEE_PASSWORD_COL = "employee.password";
    public static final String ROLE_ID_COL = "employee_role.id";
    public static final String ROLE_NAME_COL = "employee_role.name";

    @Override
    public EmployeePojo getEmployeeById(String id) throws DataAccessException {
        String sql = "SELECT * FROM cfm.employee join cfm.employee_role on " + EMPLOYEE_ROLE_ID_COL + " = "
                + ROLE_ID_COL + " and " + EMPLOYEE_ID_COL + "=?";
        return getJdbcTemplate().queryForObject(sql, new Object[] { id }, new EmployeeMapper());
    }

    @Override
    public void createEmployee(EmployeePojo employee) throws DataAccessException {
        String roleid = null;
        if (employee.getRoleName() != null) {

            String roleIdSql = "select " + ROLE_ID_COL + " from employee_role where " + ROLE_NAME_COL + "=\'"
                    + employee.getRoleName() + "\'";
            System.out.println(roleIdSql);
            roleid = getJdbcTemplate().queryForObject(roleIdSql, String.class);
        }
        String sql = "insert into employee values(?,?,?,?,?)";

        getJdbcTemplate().update(
                sql,
                new Object[] { employee.getId(), employee.getFirstName(), employee.getLastName(), roleid,
                        employee.getPassword() });

    }

    @Override
    public void deleteEmployeer(EmployeePojo employee) throws DataAccessException {
        String sql = "delete from employee where " + EMPLOYEE_ID_COL + "=?";

        getJdbcTemplate().update(sql, employee.getId());

    }

    @Override
    public void updateEmployee(EmployeePojo employee) throws DataAccessException {
        String roleid = null;
        if (employee.getRoleName() != null) {

            String roleIdSql = "select " + ROLE_ID_COL + " from employee_role where " + ROLE_NAME_COL + "=\'"
                    + employee.getRoleName() + "\'";
            System.out.println(roleIdSql);
            roleid = getJdbcTemplate().queryForObject(roleIdSql, String.class);
        }

        String sql = "update employee set " + EMPLOYEE_FIRST_NAME_COL + "=?, " + EMPLOYEE_LAST_NAME_COL + "=?, "
                + EMPLOYEE_PASSWORD_COL + "=?, " + EMPLOYEE_ROLE_ID_COL + "=? where idemployee=?";

        getJdbcTemplate().update(
                sql,
                new Object[] { employee.getFirstName(), employee.getLastName(), employee.getPassword(), roleid,
                        employee.getId() });

    }

}
