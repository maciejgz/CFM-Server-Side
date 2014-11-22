package pl.mg.cfm.ws.test.security;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import pl.mg.cfm.message.CFMJsonSimpleMessage;
import pl.mg.cfm.pojo.CarPojo;
import pl.mg.cfm.resteasy.util.AllTrustingClientFactory;
import pl.mg.cfm.serializer.CarSerializer;

public class LoginTest {

    @Test
    public void test() {

        Client client = AllTrustingClientFactory.createAlltrustedClient();
        WebTarget target = client.target("https://localhost:8444/cfm-web/car/");

        Response response = target.request().get();

        DefaultHttpClient http = new DefaultHttpClient();
        HttpGet get = new HttpGet("https://localhost:8444/cfm-web/car/");
        UsernamePasswordCredentials creds = new UsernamePasswordCredentials("9996", "password");
        get.setHeader(BasicScheme.authenticate(creds, "UTF-8", false));
        try {
            org.apache.http.HttpResponse httpResponse = http.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }

       /* CFMJsonSimplyMessage message = response.readEntity(CFMJsonSimplyMessage.class);
        System.out.println(message.toString());

        CarSerializer serializer = new CarSerializer();

        List<CarPojo> cars = serializer.deserializeList((String) message.getData());
        Iterator<CarPojo> carIt = cars.iterator();

        while (carIt.hasNext()) {
            System.out.println(carIt.next().toString());
        }
        response.close();*/
    }

}
