package pl.mg.cfm.webclient.cacrepository.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.mg.cfm.domain.CarPojo;
import pl.mg.cfm.webclient.data.entity.Car;
import pl.mg.cfm.webclient.data.repository.CarRepository;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/applicationContextPersistence.xml"})
public class SelectAllCarsTest {

    @Inject
    CarRepository repository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        if (repository == null) {
            System.out.println("repository is null");
        }

        List<Car> cars = repository.getAllCars();
        Iterator<Car> it = cars.iterator();
        while (it.hasNext()) {
            System.out.println(((Car) it.next()).toString());
        }

        assertNotNull(cars);
    }
}
