package pl.mg.cfm.commons.dao;

import java.util.List;

import javax.ejb.Remote;

import pl.mg.cfm.pojo.CarPojo;

/**
 * Interfejs DAO JPA dla CFM
 * 
 * @author m
 *
 */
@Remote
public interface CFMDao {

    //TODO pozamieniać wszędzie zwracane obiekety na rzucanie wyjątków
    //TODO mozna zrobic sesje po zalogowaniu z id przekazywanym na liscie parametrow, aby zapobiec zmianom, ktore sa niedozwolone dla uzytkownika
    /**
     * Listowanie wszystkich samochodów
     * 
     * @return
     */
    public List<CarPojo> getAllCars();

    /**
     * Znajdowanie samochodu na podstawie numeru rejestracyjnego
     * 
     * @param carId
     * @return
     */
    public CarPojo findCar(String carId);

    
    public List<CarPojo> findUserCars(int userId);

    /**
     * Logowanie uzytkownika. Zwracany kod oznacza rezultat logowania.
     * 
     * @param userId
     * @param password
     * @return
     */
    public int login(int userId, String password);

   /**
    * Aktualizacja połozenia samochodu
    * @param carId
    * @param lattitude
    * @param longitude
    * @return
    */
    public boolean updateCarPosition(String carId, boolean lattitude, double longitude);
    
    public void setCarOwner(int userId, String carId);

}
