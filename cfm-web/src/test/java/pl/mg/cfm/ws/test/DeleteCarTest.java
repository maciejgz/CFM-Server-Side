package pl.mg.cfm.ws.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Test;

import pl.mg.cfm.message.CFMJsonSimplyMessage;
import pl.mg.cfm.resteasy.util.AllTrustingClientFactory;

public class DeleteCarTest {

    @Test
    public void test() {

        Client client = AllTrustingClientFactory.createAlltrustedClient();
        String carId = "wscap50";
        WebTarget target = client.target("https://localhost:8444/cfm-web/car/" + carId);

        Response response = target.request().delete();
        CFMJsonSimplyMessage message = response.readEntity(CFMJsonSimplyMessage.class);
        System.out.println(message.toString());

        response.close();

    }

}
