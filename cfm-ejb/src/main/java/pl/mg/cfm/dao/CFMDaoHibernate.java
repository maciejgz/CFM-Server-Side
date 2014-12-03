package pl.mg.cfm.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import pl.mg.cfm.commons.dao.CFMDao;
import pl.mg.cfm.dao.exceptions.CarNotFoundException;
import pl.mg.cfm.dao.exceptions.InvalidPasswordException;
import pl.mg.cfm.dao.exceptions.ObjectAlreadyExists;
import pl.mg.cfm.dao.exceptions.UserNotFoundException;
import pl.mg.cfm.model.Car;
import pl.mg.cfm.model.Employee;
import pl.mg.cfm.pojo.CarPojo;
import pl.mg.cfm.pojo.EmployeePojo;
import pl.mg.cfm.pojo.EmployeeRolePojo;

/**
 * 
 * @author m
 *
 */
@Stateless(name = "CFMDaoHibernate")
@EJB(name = "java:global/cfm/CFMDaoHibernate", beanInterface = CFMDao.class, beanName = "CFMDaoHibernate")
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
        List<Car> cars = em.createQuery("SELECT e FROM Car e").getResultList();
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
        Query query = em.createNativeQuery(sqlQuery, Car.class);
        query.setParameter(1, carId);

        Car car = (Car) query.getSingleResult();
        return new CarPojo(car.getCar_id(), car.getDistance(), car.getLatitude(), car.getLongitude(), car.getOwner()
                .getIdemployee());
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

    @Override
    public void insertCar(CarPojo car) throws UserNotFoundException, ObjectAlreadyExists {

        if (checkIfCarExists(em, car.getCarId())) {
            throw new ObjectAlreadyExists("Car already exists. Id=" + car.getCarId());
        }

        Employee owner = null;

        if (car.getOwnerId() != null) {
            if (!checkIfEmployeeExists(em, car.getOwnerId())) {
                throw new UserNotFoundException("User with id=" + car.getOwnerId() + " not exists!");
            }
            owner = em.find(Employee.class, car.getOwnerId());
        }

        Car newCar = new Car();
        newCar.setCar_id(car.getCarId());
        newCar.setDistance(car.getDistance());
        newCar.setLatitude(car.getLatitude());
        newCar.setLongitude(car.getLongitude());
        if (owner != null) {
            newCar.setOwner(owner);
        }

        em.persist(newCar);
    }

    @Override
    public void updateCar(CarPojo car) throws CarNotFoundException, UserNotFoundException {
        if (!checkIfCarExists(em, car.getCarId())) {
            throw new CarNotFoundException("Car not exists. Id=" + car.getCarId());
        }

        Employee owner = null;

        if (car.getOwnerId() != null) {
            if (!checkIfEmployeeExists(em, car.getOwnerId())) {
                throw new UserNotFoundException("User with id=" + car.getOwnerId() + " not exists!");
            }
            owner = em.find(Employee.class, car.getOwnerId());
        }

        Car newCar = new Car();
        newCar.setCar_id(car.getCarId());
        if (car.getDistance() != null)
            newCar.setDistance(car.getDistance());

        if (car.getLatitude() != null)
            newCar.setLatitude(car.getLatitude());

        if (car.getLongitude() != null)
            newCar.setLongitude(car.getLongitude());
        if (car.getOwnerId() != null)
            newCar.setOwner(owner);

        em.merge(newCar);
    }

    private boolean checkIfCarExists(EntityManager em, String carId) {
        Car oldCar = em.find(Car.class, carId);
        if (oldCar != null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkIfEmployeeExists(EntityManager em, Integer employeeId) {
        Employee employee = em.find(Employee.class, employeeId);
        if (employee != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteCar(String carId) throws CarNotFoundException {
        if (!checkIfCarExists(em, carId)) {
            throw new CarNotFoundException("Car already not exists. Id=" + carId);
        }

        em.remove(em.find(Car.class, carId));
    }

    @Override
    public EmployeePojo getEmployee(Integer id) throws UserNotFoundException {
        String sqlQuery = "select * from employee where idemployee like ?";
        Query query = em.createNativeQuery(sqlQuery, Employee.class);
        query.setParameter(1, id);

        Employee employee = (Employee) query.getSingleResult();
        if (employee == null) {
            throw new UserNotFoundException("User not found");
        }
        EmployeePojo emPojo = new EmployeePojo();
        emPojo.setId(employee.getIdemployee());
        emPojo.setFirstName(employee.getFirstName());
        emPojo.setLastName(employee.getLastName());
        emPojo.setRoleName(employee.getRole().getName());
        return emPojo;
    }

    @Override
    public String getUserRole(String username) throws UserNotFoundException {
        String sqlQuery = "select roles.name from employee_role roles, employee emps where emps.idemployee = ? and emps.role_id=roles.id";
        Query query = em.createNativeQuery(sqlQuery);
        query.setParameter(1, username);
        String role = (String) query.getSingleResult();
        if (role == null) {
            throw new UserNotFoundException("User not found");
        }
        return role;
    }

    @Override
    public List<EmployeePojo> getAllEmployees() throws UnsupportedOperationException {
        List<Employee> employees = em.createQuery("SELECT e FROM Employee e").getResultList();
        ArrayList<EmployeePojo> employeesPojoList = new ArrayList<EmployeePojo>();
        if (employees != null) {
            Iterator<Employee> it = employees.iterator();
            while (it.hasNext()) {
                Employee employee = (Employee) it.next();
                if (employee != null) {
                    logger.debug(employee.getIdemployee());
                    logger.debug(employee.getFirstName());
                    logger.debug(employee.getLastName());
                    logger.debug(employee.getRole().getName());

                    EmployeePojo emPojo = new EmployeePojo();
                    emPojo.setFirstName(employee.getFirstName());
                    emPojo.setLastName(employee.getLastName());
                    emPojo.setId(employee.getIdemployee());
                    emPojo.setRoleName(employee.getRole().getName());
                    
                    employeesPojoList.add(emPojo);
                }
            }
        }

        return employeesPojoList;
    }

}
