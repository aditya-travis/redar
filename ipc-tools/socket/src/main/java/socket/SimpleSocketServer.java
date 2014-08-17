package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by meng on 6/1/14.
 */
public class SimpleSocketServer {
    private int port;
    private volatile boolean stopped = false;

    public SimpleSocketServer(int port) throws Exception{
        this.port = port;

        listen(port);
    }

    private void listen(int port) throws Exception{
        final ServerSocket serverSocket = new ServerSocket(port);

        while(!stopped){

            Socket clientSocket = serverSocket.accept();

            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            String inputString;
            while((inputString = inputStream.readUTF()) != null){
                //System.out.println(inputString);
                outputStream.writeUTF("This is Hello");
            }
        }
    }

    public static void main(String[] args) throws Exception{
        new SimpleSocketServer(61666);
    }
}
