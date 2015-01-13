package pl.mg.cfm.webclient.data.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Repository;

import pl.mg.cfm.domain.CarPojo;
import pl.mg.cfm.webclient.data.entity.Car;
import pl.mg.cfm.webclient.data.entity.Employee;

/**
 * Repozytorium dla klienta webowego zrealizowane na JPA (w implementacji
 * Hibernate) ze wsparciem Springa
 * 
 * @author Maciej Gzik
 *
 */
@Repository("CarRepository")
public class CarRepositoryImpl implements CarRepository {

    private Logger logger = Logger.getLogger(CarRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CarPojo> getAllCars() throws UnsupportedOperationException {
        List<Car> cars = entityManager.createQuery("SELECT e FROM Car e").getResultList();
        ArrayList<CarPojo> carsPojoList = new ArrayList<CarPojo>();
        if (cars != null) {
            Iterator<Car> it = cars.iterator();
            while (it.hasNext()) {
                Car car = (Car) it.next();
                if (car != null) {
                    logger.debug(car.getCar_id());
                    logger.debug(car.getDistance());
                    logger.debug(car.getLatitude());
                    logger.debug(car.getLongitude());
                    logger.debug(car.getOwner());

                    Employee employee = car.getOwner();
                    Integer ownerId = null;

                    if (employee != null) {
                        ownerId = employee.getIdemployee();
                    }
                    carsPojoList.add(new CarPojo(car.getCar_id(), car.getDistance(), car.getLatitude(), car
                            .getLongitude(), ownerId));
                }
            }
        }
        return carsPojoList;
    }

    @Override
    public CarPojo getCar(String carId) throws UnsupportedOperationException {
        String sqlQuery = "select * from car where car_id like ?";
        Query query = entityManager.createNativeQuery(sqlQuery, Car.class);
        query.setParameter(1, carId);

        Car car = (Car) query.getSingleResult();
        CarPojo carPojo = new CarPojo();
        carPojo.setCarId(car.getCar_id());
        carPojo.setDistance(car.getDistance());
        carPojo.setLatitude(car.getLatitude());
        carPojo.setLongitude(car.getLongitude());
        if (car.getOwner() != null) {
            carPojo.setOwnerId(car.getOwner().getIdemployee());
        }
        return carPojo;
    }

}
