package pl.mg.cfm.ws.test;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import pl.mg.cfm.domain.CarPojo;
import pl.mg.cfm.resteasy.util.AllTrustingClientFactory;
import pl.mg.cfm.serializer.CarSerializer;

public class UpdateCarTest {

    @Test
    public void test() {
        String carId = "wscap50";
        CarPojo carToInsert = new CarPojo(carId, 120L, null, 0.012, null);

        Client client = AllTrustingClientFactory.createAlltrustedClient();
        WebTarget target = client.target("https://localhost:8444/cfm-web/car/" + carId);

        Invocation.Builder invocationBuilder = target.request("text/plain").header("some", "header");

        target.queryParam("carId", carId);

        CarSerializer serializer = new CarSerializer();
        String car = serializer.serialize(carToInsert);
        Entity<String> entity = Entity.entity(car, MediaType.APPLICATION_JSON);

        Invocation invocation = invocationBuilder.buildPost(entity);
        Response response = target.request().put(entity);
        String responseValue = response.readEntity(String.class);
        response.close();
        System.out.println(responseValue);
    }
}
