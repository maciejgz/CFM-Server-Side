package pl.mg.cfm.commons.test;

import java.util.ArrayList;

import org.junit.Test;

import pl.mg.cfm.domain.CarPojo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonCarTest {

    @Test
    public void test() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        CarPojo car = new CarPojo("test", new Double(21), new Double(31.0), new Double(31.0), new Integer(12));
        CarPojo car2 = new CarPojo("test", new Double(21), new Double(31.0), new Double(31.0), new Integer(12));

        ArrayList<CarPojo> cars = new ArrayList<CarPojo>();
        cars.add(car2);
        cars.add(car);

        System.out.println(gson.toJson(cars));

        System.out.println(gson.toJson(car));
        System.out.println(gson.toJson(car2));
    }

}
