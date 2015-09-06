package pl.mg.cfm.webclient.cacrepository.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.mg.cfm.dao.exceptions.ObjectAlreadyExists;
import pl.mg.cfm.webclient.data.repository.CarRepository;

/**
 * Created by maciek on 2015-09-05.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/applicationContextPersistence.xml"})

public class CreateCarTest {

    @Autowired
    CarRepository repository;


    //@Test
    public void createCarWithNoOwnerTest() {
        try {
            System.out.println(repository.createCar("wsx1234", null, 1.0, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(expected = ObjectAlreadyExists.class)
    public void createCarAlreadyExist() throws ObjectAlreadyExists {
        repository.createCar("wsx1235", null, 1.0, null);
    }
}
