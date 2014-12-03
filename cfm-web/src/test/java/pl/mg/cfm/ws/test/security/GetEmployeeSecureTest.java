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
import pl.mg.cfm.pojo.EmployeePojo;
import pl.mg.cfm.resteasy.util.AllTrustingClientFactory;
import pl.mg.cfm.serializer.CFMMessageSerializer;
import pl.mg.cfm.serializer.EmployeeSerializer;

public class GetEmployeeSecureTest {

    @Test
    public void test() {
        DefaultHttpClient client = null;
        try {
            client = (DefaultHttpClient) AllTrustingClientFactory.createAllTrustingHttpClient();
            UsernamePasswordCredentials creds = new UsernamePasswordCredentials("9996", "testPass");
            HttpGet get = new HttpGet("https://192.168.1.104:8444/cfm-web/employee/9996");
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

                    EmployeePojo employee = (new EmployeeSerializer()).deserialize(message.getData());
                    System.out.println(employee.toString());
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
