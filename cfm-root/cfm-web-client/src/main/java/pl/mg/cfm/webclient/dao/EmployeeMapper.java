package pl.mg.cfm.webclient.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.mg.cfm.pojo.EmployeePojo;

public class EmployeeMapper implements RowMapper<EmployeePojo> {

    @Override
    public EmployeePojo mapRow(ResultSet rs, int rowNum) throws SQLException {
        EmployeePojo empl = new EmployeePojo();
        empl.setId(rs.getInt(CFMEmployeeDAOImplementation.EMPLOYEE_ID_COL));
        empl.setFirstName(rs.getString(CFMEmployeeDAOImplementation.EMPLOYEE_FIRST_NAME_COL));
        empl.setLastName(rs.getString(CFMEmployeeDAOImplementation.EMPLOYEE_LAST_NAME_COL));
        empl.setPassword(rs.getString(CFMEmployeeDAOImplementation.EMPLOYEE_PASSWORD_COL));
        empl.setRoleName(rs.getString(CFMEmployeeDAOImplementation.ROLE_NAME_COL));
        return empl;
    }

}
