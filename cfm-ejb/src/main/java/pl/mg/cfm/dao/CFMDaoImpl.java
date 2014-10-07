package pl.mg.cfm.dao;

import java.util.List;

import pl.mg.cfm.commons.dao.CFMDao;
import pl.mg.cfm.pojo.CarPojo;

public class CFMDaoImpl implements CFMDao {

    @Override
    public List<CarPojo> getAllCars() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CarPojo findCar(String carId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CarPojo> findUserCars(int userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean updateCarPosition(String carId, boolean lattitude, double longitude) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setCarOwner(int newCarUserId, String carId) {
        // TODO Auto-generated method stub
        
    }

}
