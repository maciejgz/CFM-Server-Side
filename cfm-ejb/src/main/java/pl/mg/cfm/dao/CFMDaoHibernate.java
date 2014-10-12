package pl.mg.cfm.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.mg.cfm.commons.dao.CFMDao;
import pl.mg.cfm.pojo.CarPojo;

@Stateless
public class CFMDaoHibernate implements CFMDao {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarPojo findCar(String carId) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarPojo> findUserCars(int userId)
			throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateCarPosition(String carId, boolean lattitude,
			double longitude) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCarOwner(int newCarUserId, String carId)
			throws UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean login(String username, String password)
			throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

}
