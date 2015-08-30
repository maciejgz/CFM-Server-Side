package pl.mg.cfm.webclient.dao.test;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.mg.cfm.domain.CarPojo;
import pl.mg.cfm.webclient.data.dao.CFMCarDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/applicationContextPersistence.xml" })
public class UpdateCarTest {

    @Inject
    CFMCarDao dao;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        if (dao == null) {
            System.out.println("dao is null");
        }
        CarPojo car = new CarPojo();
        car.setCarId("2");
        car.setDistance(11D);
        car.setLatitude(null);
        car.setLongitude(null);
        car.setOwnerId(null);

        dao.updateCar(car);
    }
}
