package http.embeded;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.server.ssl.SslSelectChannelConnector;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by meng on 7/19/15.
 */
public class SimpleHttpHandler extends AbstractHandler {

    private Server server;

    public SimpleHttpHandler() {
        this.server = new Server();
        SelectChannelConnector connector0 = new SelectChannelConnector();
        connector0.setPort(8888);
        connector0.setMaxIdleTime(30000);
        connector0.setRequestHeaderSize(8192);

        SelectChannelConnector connector1 = new SelectChannelConnector();
        connector1.setHost("127.0.0.1");
        connector1.setPort(8843);
        connector1.setThreadPool(new QueuedThreadPool(20));
        connector1.setName("admin");
        server.setConnectors(new Connector[]{ connector0, connector1 });

        server.setHandler(this);
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("target: " + target);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().println("<h1>Hello World</h1>");
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public static void main(String[] args) throws Exception {
        Server server = new SimpleHttpHandler().getServer();
        server.start();
        server.join();
    }
}
