package pl.mg.cfm.webclient.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.mg.cfm.pojo.EmployeePojo;

public class EmployeeMapper implements RowMapper<EmployeePojo> {

    @Override
    public EmployeePojo mapRow(ResultSet rs, int rowNum) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

}
