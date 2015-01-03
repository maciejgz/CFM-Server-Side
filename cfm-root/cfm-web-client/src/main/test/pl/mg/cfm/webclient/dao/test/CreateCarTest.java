package pl.mg.cfm.webclient.dao.test;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.mg.cfm.domain.CarPojo;
import pl.mg.cfm.webclient.dao.CFMCarDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml", "classpath:applicationContextDb.xml" })
public class CreateCarTest {

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
        car.setDistance(0L);
        car.setLatitude(null);
        car.setLongitude(null);
        car.setOwnerId(null);

        dao.createCar(car);

    }
}
