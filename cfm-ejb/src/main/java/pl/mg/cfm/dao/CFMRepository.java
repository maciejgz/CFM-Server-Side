package pl.mg.cfm.dao;

import java.util.List;

import javax.persistence.EntityManager;

import pl.mg.cfm.commons.dao.CFMDao;
import pl.mg.cfm.dao.exceptions.CarNotFoundException;
import pl.mg.cfm.dao.exceptions.InvalidPasswordException;
import pl.mg.cfm.dao.exceptions.ObjectAlreadyExists;
import pl.mg.cfm.dao.exceptions.UserNotFoundException;
import pl.mg.cfm.pojo.CarPojo;
import pl.mg.cfm.pojo.EmployeePojo;

public class CFMRepository implements CFMDao {

    @Override
    public EntityManager getEm() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setEm(EntityManager em) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<CarPojo> getAllCars() throws UnsupportedOperationException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CarPojo getCar(String carId) throws UnsupportedOperationException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CarPojo> findUserCars(int userId) throws UnsupportedOperationException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean updateCarPosition(String carId, boolean lattitude, double longitude)
            throws UnsupportedOperationException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setCarOwner(int newCarUserId, String carId) throws UnsupportedOperationException {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean login(String username, String password) throws UnsupportedOperationException, UserNotFoundException,
            InvalidPasswordException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getUserRole(String username) throws UserNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insertCar(CarPojo car) throws UserNotFoundException, ObjectAlreadyExists {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateCar(CarPojo car) throws CarNotFoundException, UserNotFoundException {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteCar(String carId) throws CarNotFoundException {
        // TODO Auto-generated method stub

    }

    @Override
    public EmployeePojo getEmployee(Integer id) throws UserNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<EmployeePojo> getAllEmployees() throws UnsupportedOperationException {
        // TODO Auto-generated method stub
        return null;
    }

}
