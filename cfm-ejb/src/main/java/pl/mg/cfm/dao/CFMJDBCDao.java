package pl.mg.cfm.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import org.jboss.logging.Logger;

import pl.mg.cfm.commons.dao.CFMDao;
import pl.mg.cfm.dao.exceptions.InvalidPasswordException;
import pl.mg.cfm.dao.exceptions.UserNotFoundException;
import pl.mg.cfm.pojo.CarPojo;

@Stateless(name = "CFMJDBCDao")
@EJB(name = "java:global/cfm/CFMJDBCDao", beanInterface = CFMDao.class, beanName = "CFMJDBCDao")
public class CFMJDBCDao {

    Logger logger = Logger.getLogger(CFMJDBCDao.class);

//    @Override
    public EntityManager getEm() {
        // TODO Auto-generated method stub
        return null;
    }

//    @Override
    public void setEm(EntityManager em) {
        // TODO Auto-generated method stub

    }

//    @Override
    public List<CarPojo> getAllCars() throws UnsupportedOperationException {

        throw new UnsupportedOperationException();
    }

//    @Override
    public CarPojo getCar(String carId) throws UnsupportedOperationException {

        throw new UnsupportedOperationException();
    }

//    @Override
    public List<CarPojo> findUserCars(int userId) throws UnsupportedOperationException {

        throw new UnsupportedOperationException();
    }

//    @Override
    public boolean updateCarPosition(String carId, boolean lattitude, double longitude)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

//    @Override
    public void setCarOwner(int newCarUserId, String carId) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

//    @Override
    public boolean login(String username, String password) throws UnsupportedOperationException, UserNotFoundException,
            InvalidPasswordException {
        throw new UnsupportedOperationException();
    }

}
