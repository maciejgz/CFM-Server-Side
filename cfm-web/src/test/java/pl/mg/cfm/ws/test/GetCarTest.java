package pl.mg.cfm.ws.test;

import java.util.Iterator;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Test;

import pl.mg.cfm.message.CFMJsonSimplyMessage;
import pl.mg.cfm.pojo.CarPojo;
import pl.mg.cfm.resteasy.util.AllTrustingClientFactory;
import pl.mg.cfm.serializer.CarSerializer;

public class GetCarTest {

    @Test
    public void test() {
        Client client = AllTrustingClientFactory.createAlltrustedClient();
        WebTarget target = client.target("https://localhost:8444/cfm-web/car/wsc1234");

        Response response = target.request().get();
        CFMJsonSimplyMessage message = response.readEntity(CFMJsonSimplyMessage.class);
        System.out.println(message.toString());

        CarSerializer serializer = new CarSerializer();

        if (message.getData() instanceof List<?>) {
            List<CarPojo> cars = serializer.deserializeList((String) message.getData());
            Iterator<CarPojo> carIt = cars.iterator();

            while (carIt.hasNext()) {
                System.out.println(carIt.next().toString());
            }
        } else {
            CarPojo car = serializer.deserialize((String) message.getData());
            System.out.println(car.toString());
        }

        response.close();
    }

}
