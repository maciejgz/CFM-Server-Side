package pl.mg.cfm.webclient.cacrepository.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.mg.cfm.domain.CarPojo;
import pl.mg.cfm.webclient.data.repository.CarRepository;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/applicationContextPersistence.xml"})
public class SelectEmployeeCarsTest {

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

        List<CarPojo> cars = repository.getEmployeeCars("1");

        for (CarPojo car: cars){
            System.out.println(car);
        }
        assertEquals(3,cars.size());
    }
}
