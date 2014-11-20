package pl.mg.cfm.ws.test.security;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import pl.mg.cfm.resteasy.util.AllTrustingClientFactory;

public class DefaultHttpLoginTest {

    @Test
    public void test() {

        DefaultHttpClient client = null;
        try {
            client = (DefaultHttpClient) AllTrustingClientFactory.createAllTrustingHttpClient();
            UsernamePasswordCredentials creds = new UsernamePasswordCredentials("9996", "testPass");
            HttpGet get = new HttpGet("https://localhost:8444/cfm-web/car/");
            get.setHeader(BasicScheme.authenticate(creds, "UTF-8", false));
            try {
                org.apache.http.HttpResponse httpResponse = client.execute(get);
                System.out.println(httpResponse.getStatusLine().getStatusCode() + ";"
                        + httpResponse.getStatusLine().getReasonPhrase());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (GeneralSecurityException e) {

            e.printStackTrace();
        }
    }
}
