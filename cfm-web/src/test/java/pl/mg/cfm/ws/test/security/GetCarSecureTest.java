package pl.mg.cfm.ws.test.security;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.http.HttpEntity;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import pl.mg.cfm.message.CFMJsonSimpleMessage;
import pl.mg.cfm.pojo.CarPojo;
import pl.mg.cfm.resteasy.util.AllTrustingClientFactory;
import pl.mg.cfm.serializer.CFMMessageSerializer;
import pl.mg.cfm.serializer.CarSerializer;

public class GetCarSecureTest {

    @Test
    public void test() {
        DefaultHttpClient client = null;
        try {
            client = (DefaultHttpClient) AllTrustingClientFactory.createAllTrustingHttpClient();
            UsernamePasswordCredentials creds = new UsernamePasswordCredentials("9995", "pass");
            HttpGet get = new HttpGet("https://localhost:8444/cfm-web/car/wscap48");
            get.setHeader(BasicScheme.authenticate(creds, "UTF-8", false));
            try {
                org.apache.http.HttpResponse httpResponse = client.execute(get);
                System.out.println(httpResponse.getStatusLine().getStatusCode() + ";"
                        + httpResponse.getStatusLine().getReasonPhrase());
                if (httpResponse.getStatusLine().getStatusCode() == 200) {

                    HttpEntity entity = httpResponse.getEntity();
                    String entityContents = EntityUtils.toString(entity);
                    System.out.println(entityContents);

                    CFMMessageSerializer serializer = new CFMMessageSerializer();
                    CFMJsonSimpleMessage message = serializer.deserialize(entityContents);
                    System.out.println(message.toString());

                    CarPojo car = (new CarSerializer()).deserialize(message.getData());
                    System.out.println(car.toString());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (GeneralSecurityException e) {

            e.printStackTrace();
        } finally {
        }
    }

}
