package pl.mg.cfm.commons.test;

import org.junit.Test;

import pl.mg.cfm.pojo.CarPojo;
import pl.mg.cfm.serializer.CarSerializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ToGsonCarTest {

    @Test
    public void test() {
        String input = "[{\"id\":null,\"carId\":\"test\",\"distance\":21,\"latitude\":31.0,\"longitude\":31.0,\"ownerId\":12},{\"id\":1,\"carId\":\"test\",\"distance\":21,\"latitude\":31.0,\"longitude\":31.0,\"ownerId\":12}]";

        String oneCar = "{\"id\":null,\"carId\":\"test\",\"distance\":21,\"latitude\":31.0,\"longitude\":31.0,\"ownerId\":12}";

        Gson gson = new GsonBuilder().registerTypeAdapter(CarPojo.class, new CarSerializer()).serializeNulls().create();

        CarPojo car = gson.fromJson(oneCar, CarPojo.class);
        System.out.println(car);

    }

}
