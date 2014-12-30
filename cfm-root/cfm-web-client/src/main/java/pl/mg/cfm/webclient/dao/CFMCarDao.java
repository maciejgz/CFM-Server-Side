package pl.mg.cfm.webclient.dao;

import org.springframework.dao.DataAccessException;

import pl.mg.cfm.pojo.CarPojo;

public interface CFMCarDao {

    public CarPojo getCarById(String id) throws DataAccessException;

    public void createCar(CarPojo car) throws DataAccessException;

    public void deleteCar(CarPojo car) throws DataAccessException;

    public void updateCar(CarPojo car) throws DataAccessException;
}
