package pl.mg.cfm.webclient.data.repository;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.mg.cfm.webclient.data.entity.Car;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Repozytorium dla klienta webowego zrealizowane na JPA (w implementacji
 * Hibernate) ze wsparciem Springa
 *
 * @author Maciej Gzik
 */
@Repository("CarRepository")
public class CarRepositoryImpl implements CarRepository {

    private Logger logger = Logger.getLogger(CarRepositoryImpl.class);

    @PersistenceContext(name = "cfm-localhost")
    private EntityManager entityManager;

    @Override
    public List<Car> getAllCars() throws UnsupportedOperationException {
        List<Car> cars = entityManager.createQuery("SELECT e FROM Car e").getResultList();
/*        ArrayList<CarPojo> carsPojoList = new ArrayList<CarPojo>();
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
        }*/
        return cars;
    }

    @Override
    public Car getCar(String carId) throws UnsupportedOperationException {
        String sqlQuery = "select * from car where car_id like ?";
        Query query = entityManager.createNativeQuery(sqlQuery, Car.class);
        query.setParameter(1, carId);

        Car car = (Car) query.getSingleResult();
/*        CarPojo carPojo = new CarPojo();
        carPojo.setCarId(car.getCar_id());
        carPojo.setDistance(car.getDistance());
        carPojo.setLatitude(car.getLatitude());
        carPojo.setLongitude(car.getLongitude());
        if (car.getOwner() != null) {
            carPojo.setOwnerId(car.getOwner().getIdemployee());
        }*/
        return car;
    }

    @Override
    public List<Car> findCars(CarCriteria criteria) throws UnsupportedOperationException {
        StringBuffer sqlQuery = new StringBuffer();
        sqlQuery.append("select * from car, employee where ");
        // TODO implement
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Car> getEmployeeCars(String employeeId) throws UnsupportedOperationException {
      /*  String queryString = "select * from car where car_owner_id like ?";

        TypedQuery<CarPojo> query = entityManager.createQuery(queryString, CarPojo.class);
        query.setParameter(1, employeeId);

        List<CarPojo> result = query.getResultList();*/


        //CriteriaBuilder
        /*CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(Car.class);

        cq.where(cb.like(root.get("owner"), employeeId));
        Query criteriaQuery = entityManager.createQuery(cq);

        List<Car> resultCriteria = criteriaQuery.getResultList();

        return resultCriteria;*/


        //NamedQuery version
        return entityManager.createNamedQuery("car.getEmployeeCars").setParameter("employee_id", Integer.valueOf(employeeId)).getResultList();
    }

}
