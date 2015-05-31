package pl.mg.cfm.webclient.data.adapter;

import org.springframework.stereotype.Component;
import pl.mg.cfm.domain.CarPojo;
import pl.mg.cfm.webclient.data.entity.Car;
import pl.mg.cfm.webclient.data.entity.Employee;

/**
 * Created by m on 2015-05-17.
 */
@Component
public class CarAdapter implements PojoAdapter<CarPojo, Car> {
    @Override
    public CarPojo fromEntity(Car entity) {
        if (entity == null) {
            return null;
        }
        CarPojo car = new CarPojo();
        car.setCarId(entity.getCar_id());
        car.setDistance(entity.getDistance());
        car.setLatitude(entity.getLatitude());
        car.setLongitude(entity.getLongitude());
        car.setOwnerId(entity.getOwner().getIdemployee());
        return car;
    }

    @Override
    @Deprecated
    public Car toEntity(CarPojo pojo) {
        if (pojo == null) {
            return null;
        }

        Car car = new Car();
        car.setCar_id(pojo.getCarId());
        car.setLatitude(pojo.getLatitude());
        car.setLongitude(pojo.getLongitude());
        car.setDistance(pojo.getDistance());

        Employee empl = new Employee();
        empl.setIdemployee(pojo.getOwnerId());

        car.setOwner(empl);
        return car;
    }
}
