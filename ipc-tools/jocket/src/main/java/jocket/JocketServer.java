package jocket;

import jocket.net.JocketSocket;
import jocket.net.ServerJocket;
import org.apache.commons.io.IOUtils;

/**
 * Created by meng on 6/1/14.
 */
public class JocketServer {

    private ServerJocket serverJocket;

    private int port;

    public JocketServer(int port) throws Exception{
        this.port = port;
        this.serverJocket = new ServerJocket(port);
        start();
    }

    public void start() throws Exception{

        while(true){
            pipeToStdInOut(serverJocket.accept());
        }
    }

    public static void pipeToStdInOut(final JocketSocket s) {
        new Thread("Reader") {
            //@Override
            public void run() {
                try {
                    Pipe.pipe(s.getInputStream(), System.out);
                }
                catch (Exception e) {
                    s.close();
                }
            }
        }.start();

        new Thread("Writer") {
            //@Override
            public void run() {
                try {
                    Pipe.pipe(IOUtils.toInputStream("This is Hello"), s.getOutputStream());
                }
                catch (Exception e) {
                    s.close();
                }
            }
        }.start();
    }

    public static void main (String[] args) throws Exception{
        JocketServer jocketServer = new JocketServer(6100);
    }
}
