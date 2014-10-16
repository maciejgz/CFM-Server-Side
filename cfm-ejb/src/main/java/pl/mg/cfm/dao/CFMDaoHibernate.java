package pl.mg.cfm.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import pl.mg.cfm.commons.dao.CFMDao;
import pl.mg.cfm.dao.exceptions.InvalidPasswordException;
import pl.mg.cfm.dao.exceptions.UserNotFoundException;
import pl.mg.cfm.model.Car;
import pl.mg.cfm.model.Employee;
import pl.mg.cfm.pojo.CarPojo;

@Stateless
public class CFMDaoHibernate implements CFMDao {

    Logger logger = Logger.getLogger(CFMDaoHibernate.class);

    @PersistenceContext(unitName = "cfm-ejb")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<CarPojo> getAllCars() throws UnsupportedOperationException {
        List<Car> cars =  em.createQuery("SELECT e FROM Car e").getResultList();
        
        ArrayList<CarPojo> carsPojoList = new ArrayList<CarPojo>();
        if(cars!=null)
        {
            Iterator it = cars.iterator();
            while(it.hasNext()){
                Car car = (Car) it.next();
                if(car!=null){
                    carsPojoList.add(new CarPojo(car.getCarPk().getId(),car.getCarPk().getCar_id(), car.getDistance(), car.getLatitude(), car.getLongitude(), car.getOwner().getIdemployee()));
                }
            }
        }
        
        return carsPojoList;
    }

    @Override
    public CarPojo findCar(String carId) throws UnsupportedOperationException {
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

        boolean result = false;

        try {

            Integer empId = Integer.parseInt(username);

            Employee employee = em.find(Employee.class, empId);

            if (employee != null) {
                if (employee.getPassword().equals(password)) {
                    result = true;
                } else {
                    throw new InvalidPasswordException();
                }
            }
        } catch (NumberFormatException e) {
            throw new UserNotFoundException("User " + username + " not found");
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException("User " + username + " not found");
        }

        return result;

    }

}
