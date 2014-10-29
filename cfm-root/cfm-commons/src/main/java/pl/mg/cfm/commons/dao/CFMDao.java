package pl.mg.cfm.commons.dao;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.EntityManager;

import pl.mg.cfm.dao.exceptions.CarNotFoundException;
import pl.mg.cfm.dao.exceptions.InvalidPasswordException;
import pl.mg.cfm.dao.exceptions.ObjectAlreadyExists;
import pl.mg.cfm.dao.exceptions.UserNotFoundException;
import pl.mg.cfm.pojo.CarPojo;

/**
 * Interfejs DAO JPA dla CFM
 * 
 * @author Maciej Gzik
 *
 */
@Remote
public interface CFMDao {

    public EntityManager getEm();

    public void setEm(EntityManager em);

    /**
     * Listowanie wszystkich samochodów
     * 
     * @return
     */
    public List<CarPojo> getAllCars() throws UnsupportedOperationException;

    /**
     * Znajdowanie samochodu na podstawie numeru rejestracyjnego
     * 
     * @param carId
     * @return
     */
    public CarPojo getCar(String carId) throws UnsupportedOperationException;

    public List<CarPojo> findUserCars(int userId) throws UnsupportedOperationException;

    /**
     * Aktualizacja połozenia samochodu
     * 
     * @param carId
     * @param lattitude
     * @param longitude
     * @return
     */
    public boolean updateCarPosition(String carId, boolean lattitude, double longitude)
            throws UnsupportedOperationException;

    public void setCarOwner(int newCarUserId, String carId) throws UnsupportedOperationException;

    /**
     * Walidacja logowania
     * 
     * @param username
     *            nazwa uzytkownika. Wstepnie jako max 11-cyfowa liczba
     * @param password
     *            skrot hasla porownywany z baza danych
     * @return
     * @throws UnsupportedOperationException
     * @throws UserNotFoundException
     * @throws InvalidPasswordException
     */
    public boolean login(String username, String password) throws UnsupportedOperationException, UserNotFoundException,
            InvalidPasswordException;

    
    public void insertCar(CarPojo car)  throws UserNotFoundException, ObjectAlreadyExists;
    
    public void updateCar(CarPojo car) throws CarNotFoundException,UserNotFoundException;
    
    public void deleteCar(String carId) throws CarNotFoundException;
}
