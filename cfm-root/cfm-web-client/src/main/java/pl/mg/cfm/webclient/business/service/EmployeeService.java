package pl.mg.cfm.webclient.business.service;

/**
 * Interfejs serwisu operacji wykonywanych na uzytkowniku. Dzieki temu
 * oddzielamy warstwe aplikacji od bazy danych, ale jest to w pewnym sensie
 * skopiowanie funkcjonalnosci repository (nie do konca, ale jednak).
 * 
 * @author Maciej Gzik
 *
 */
public interface EmployeeService {

    public boolean login(String id, String password);

}
