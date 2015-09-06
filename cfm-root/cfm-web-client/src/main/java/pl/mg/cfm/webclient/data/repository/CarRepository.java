package pl.mg.cfm.webclient.data.repository;

import pl.mg.cfm.dao.exceptions.ObjectAlreadyExists;
import pl.mg.cfm.webclient.data.entity.Car;

import java.util.Date;
import java.util.List;

public interface CarRepository {

    public List<Car> getAllCars() throws UnsupportedOperationException;

    public Car getCar(String carId) throws UnsupportedOperationException;

    public List<Car> findCars(CarCriteria criteria) throws UnsupportedOperationException;

    public List<Car> getEmployeeCars(String employeeId) throws UnsupportedOperationException;

    public String createCar(String carId, Integer ownerId, Double distance, Date registrationDate) throws UnsupportedOperationException, ObjectAlreadyExists;
}
