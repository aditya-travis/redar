package http.embeded;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.eclipse.jetty.server.Server;

import static org.junit.Assert.*;

public class SimpleHttpHandlerTest {

    @org.junit.Test
    public void testHandle() throws Exception {
        //start the web server
        Server server = new SimpleHttpHandler().getServer();
        server.start();

        HttpClient httpClient = new HttpClient();
        GetMethod request = new GetMethod("http://localhost:8888");
        int statusCode = httpClient.executeMethod(request);
        System.out.println("Status Code: " + statusCode);
        System.out.println("Response: " + request.getResponseBodyAsString());
        server.stop();

    }
}