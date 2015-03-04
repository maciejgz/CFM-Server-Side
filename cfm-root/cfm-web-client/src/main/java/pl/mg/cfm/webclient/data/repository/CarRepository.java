package pl.mg.cfm.webclient.data.repository;

import java.util.List;

import pl.mg.cfm.domain.CarPojo;

public interface CarRepository {

    public List<CarPojo> getAllCars() throws UnsupportedOperationException;

    public CarPojo getCar(String carId) throws UnsupportedOperationException;

    public List<CarPojo> findCars(CarCriteria criteria) throws UnsupportedOperationException;
}
