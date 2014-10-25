package pl.mg.cfm.ws.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import pl.mg.cfm.pojo.CarPojo;
import pl.mg.cfm.resteasy.util.AllTrustingClientFactory;
import pl.mg.cfm.serializer.CarSerializer;

public class CreateCarTest {

    @Test
    public void test() {
        String carId = "wscap47";
        CarPojo carToInsert = new CarPojo(carId, 0, null, null, 0);

        CreateCarTest test = new CreateCarTest();
        Client client = AllTrustingClientFactory.createAlltrustedClient();
        WebTarget target = client.target("https://localhost:8444/cfm-web/car/" + carId);

        Invocation.Builder invocationBuilder = target.request("text/plain").header("some", "header");

        target.queryParam("carId", "12345");

        CarSerializer serializer = new CarSerializer();
        String car = serializer.serialize(carToInsert);
        Entity<String> entity = Entity.entity(car, MediaType.APPLICATION_JSON);

        Invocation invocation = invocationBuilder.buildPost(entity);

        Response response = target.request().post(entity);
        String responseValue = response.readEntity(String.class);
        response.close();
        System.out.println(responseValue);
    }
}
