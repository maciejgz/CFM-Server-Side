package pl.mg.cfm.webclient.dao.test;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.mg.cfm.pojo.CarPojo;
import pl.mg.cfm.webclient.dao.CFMCarDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml", "classpath:applicationContextDb.xml" })
public class CarSelectTest {

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
        CarPojo exampleCar = dao.getCarById("1");
        if (exampleCar != null)
            System.out.println(exampleCar);
        assertNotNull(exampleCar);
    }
}
