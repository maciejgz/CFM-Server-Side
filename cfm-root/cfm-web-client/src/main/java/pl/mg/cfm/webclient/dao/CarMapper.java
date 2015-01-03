package pl.mg.cfm.webclient.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.mg.cfm.domain.CarPojo;

public class CarMapper implements RowMapper<CarPojo> {

    @Override
    public CarPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
        CarPojo car = new CarPojo();
        car.setCarId(rs.getString(CFMCarDaoImplementation.CAR_ID_COL));
        car.setDistance(rs.getLong(CFMCarDaoImplementation.CAR_DISTANCE_COL));
        car.setLatitude(rs.getDouble(CFMCarDaoImplementation.CAR_LATITUDE_COL));
        car.setLongitude(rs.getDouble(CFMCarDaoImplementation.CAR_LONGITUDE_COL));
        car.setOwnerId(rs.getInt(CFMCarDaoImplementation.CAR_OWNER_ID_COL));
        return car;
    }
}
