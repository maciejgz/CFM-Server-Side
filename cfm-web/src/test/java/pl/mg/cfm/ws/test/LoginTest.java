package pl.mg.cfm.ws.test;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

public class LoginTest {

    @Test
    public void test() {
        
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://localhost:8080/cfm-web/test");
        
    }

}
