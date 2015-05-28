package pl.mg.cfm.webclient.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mg.cfm.business.exception.InvalidInputDataException;
import pl.mg.cfm.dao.exceptions.EmployeeNotFoundException;
import pl.mg.cfm.domain.CarPojo;
import pl.mg.cfm.webclient.data.adapter.CarAdapter;
import pl.mg.cfm.webclient.data.entity.Car;
import pl.mg.cfm.webclient.data.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {


    @Autowired
    private CarRepository repository;

    @Autowired
    private CarAdapter adapter;

    @Override
    public List<CarPojo> getEmployeeCars(Integer employeeId) throws EmployeeNotFoundException,
            InvalidInputDataException {
        List<Car> cars = repository.getEmployeeCars(String.valueOf(employeeId));

        ArrayList<CarPojo> result = new ArrayList<>();
        if (cars != null) {
            for (Car car : cars) {
                result.add(adapter.fromEntity(car));
            }
        }
        return result;
    }

}
