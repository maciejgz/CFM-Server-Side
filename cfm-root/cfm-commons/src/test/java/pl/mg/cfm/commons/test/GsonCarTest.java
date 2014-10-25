package pl.mg.cfm.commons.test;

import java.util.ArrayList;

import org.junit.Test;

import pl.mg.cfm.pojo.CarPojo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonCarTest {

    @Test
    public void test() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        CarPojo car = new CarPojo("test", 21, 31.0, 31.0, 12);
        CarPojo car2 = new CarPojo("test", 21, 31.0, 31.0, 12);

        ArrayList<CarPojo> cars = new ArrayList<CarPojo>();
        cars.add(car2);
        cars.add(car);

        System.out.println(gson.toJson(cars));

        System.out.println(gson.toJson(car));
        System.out.println(gson.toJson(car2));
    }

}
