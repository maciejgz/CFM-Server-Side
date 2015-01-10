package pl.mg.cfm.webclient.data.dao;

import org.springframework.dao.DataAccessException;

import pl.mg.cfm.domain.CarPojo;

public interface CFMCarDao {

    public CarPojo getCarById(String id) throws DataAccessException;

    public void createCar(CarPojo car) throws DataAccessException;

    public void deleteCar(CarPojo car) throws DataAccessException;

    public void updateCar(CarPojo car) throws DataAccessException;
    
    
}
