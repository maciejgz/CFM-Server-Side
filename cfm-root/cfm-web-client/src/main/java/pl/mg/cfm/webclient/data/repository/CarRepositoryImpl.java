package pl.mg.cfm.webclient.data.repository;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.mg.cfm.dao.exceptions.ObjectAlreadyExists;
import pl.mg.cfm.webclient.data.entity.Car;
import pl.mg.cfm.webclient.data.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
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
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> cq = cb.createQuery(Car.class);
        Root<Car> emp = cq.from(Car.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (criteria.getCarId() != null && !criteria.getCarId().equals("")) {
            SearchOperator operator = criteria.getCarIdOperator();
            switch (operator) {
                case EQ:
                    predicates.add(cb.equal(emp.<String>get("car_id"), criteria.getCarId()));
                    break;
                case GTEQ:
                    predicates.add(cb.greaterThanOrEqualTo(emp.<String>get("car_id"), criteria.getCarId()));
                    break;
                case GT:
                    predicates.add(cb.greaterThan(emp.<String>get("car_id"), criteria.getCarId()));
                    break;
                case LTEQ:
                    predicates.add(cb.lessThanOrEqualTo(emp.<String>get("car_id"), criteria.getCarId()));
                    break;
                case LT:
                    predicates.add(cb.lessThan(emp.<String>get("car_id"), criteria.getCarId()));
                    break;
                case NOTLIKE:
                    predicates.add(cb.notEqual(emp.<String>get("car_id"), criteria.getCarId()));
                    break;
                default:
                    logger.error("Operation=" + operator.toString() + " on car id search is not allowed! Expression will be omitted.");
            }
        }

        // TODO zrobiæ zagnie¿d¿one queries dla ownerId
        if (criteria.getOwnerEmployeeFirstName() != null && !criteria.getOwnerEmployeeFirstName().equals("")) {
            SearchOperator operator = criteria.getOwnerEmployeeFirstNameOperator();
            switch (operator) {
                case EQ:
                    predicates.add(cb.equal(emp.<String>get("owner.firstName"), criteria.getOwnerEmployeeFirstName()));
                    break;
                case LIKE:
                    predicates.add(cb.like(emp.<String>get("owner.firstName"), criteria.getOwnerEmployeeFirstName()));
                    break;
                case NOTLIKE:
                    predicates.add(cb.notLike(emp.<String>get("owner.firstName"), criteria.getOwnerEmployeeFirstName()));
                    break;
                default:
                    logger.error("Operation=" + operator.toString() + " on employee first name search is not allowed! Expression will be omitted.");
            }
        }

        cq.select(emp).where(predicates.toArray(new Predicate[]{}));
        TypedQuery<Car> q = entityManager.createQuery(cq);
        return q.getResultList();

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

    @Override
    @Transactional(rollbackFor = {ObjectAlreadyExists.class})
    public String createCar(String carId, Integer ownerId, Double distance, Date registrationDate) throws UnsupportedOperationException, ObjectAlreadyExists {
        try {

            Car car1 = entityManager.find(Car.class, carId);

            if (car1 != null) {
                logger.debug("car already exists");
                throw new ObjectAlreadyExists("car already exists-" + carId);
            }
            Car car = new Car();
            car.setCar_id(carId);
            if (ownerId != null) {
                Employee employee = entityManager.find(Employee.class, ownerId);
                car.setOwner(employee);
            }
            if (distance != null) {
                car.setDistance(distance);
            }
            if (registrationDate != null) {
                car.setRegistrationDate(registrationDate);
            }


            entityManager.persist(car);
            entityManager.flush();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ObjectAlreadyExists(e.getMessage());
        }
        return carId;
    }
}
