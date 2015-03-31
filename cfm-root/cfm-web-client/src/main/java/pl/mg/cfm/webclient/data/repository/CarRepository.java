package pl.mg.cfm.webclient.data.repository;

import java.util.List;

import pl.mg.cfm.domain.CarPojo;
import pl.mg.cfm.webclient.data.entity.Car;

public interface CarRepository {

    public List<Car> getAllCars() throws UnsupportedOperationException;

    public Car getCar(String carId) throws UnsupportedOperationException;

    public List<Car> findCars(CarCriteria criteria) throws UnsupportedOperationException;

    public List<Car> getEmployeeCars(String employeeId) throws UnsupportedOperationException;
}
