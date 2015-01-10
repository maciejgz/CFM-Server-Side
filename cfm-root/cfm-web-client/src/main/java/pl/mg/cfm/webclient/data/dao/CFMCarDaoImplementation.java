package pl.mg.cfm.webclient.data.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import pl.mg.cfm.domain.CarPojo;

public class CFMCarDaoImplementation extends JdbcDaoSupport implements CFMCarDao {

    public static final String CAR_ID_COL = "car_id";
    public static final String CAR_DISTANCE_COL = "distance";
    public static final String CAR_LATITUDE_COL = "latitude";
    public static final String CAR_LONGITUDE_COL = "longitude";
    public static final String CAR_OWNER_ID_COL = "car_owner_id";

    @Override
    public CarPojo getCarById(String id) throws DataAccessException {
        String sql = "select * from car where car_id=?";
        return getJdbcTemplate().queryForObject(sql, new CarMapper(), new Object[] { id });
    }

    @Override
    public void createCar(CarPojo car) throws DataAccessException {
        String sql = "insert into car values(?,?,?,?,?)";
        getJdbcTemplate().update(
                sql,
                new Object[] { car.getCarId(), car.getDistance(), car.getLatitude(), car.getLongitude(),
                        car.getOwnerId() });
    }

    @Override
    public void deleteCar(CarPojo car) throws DataAccessException {
        String sql = "delete from car where car_id=?";
        getJdbcTemplate().update(sql, new Object[] { car.getCarId() });
    }

    @Override
    public void updateCar(CarPojo car) throws DataAccessException {
        String sql = "update car set " + CAR_DISTANCE_COL + "=?, " + CAR_LATITUDE_COL + "=?, " + CAR_LONGITUDE_COL
                + "=?, " + CAR_OWNER_ID_COL + "=? where car_id=?";
        getJdbcTemplate().update(
                sql,
                new Object[] { car.getDistance(), car.getLatitude(), car.getLongitude(), car.getOwnerId(),
                        car.getCarId() });
    }
}
