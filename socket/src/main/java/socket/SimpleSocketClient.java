package socket;

import org.springframework.util.StopWatch;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by meng on 6/1/14.
 */
public class SimpleSocketClient {

    public static void main(String[] args) throws Exception{

        Socket serverSocket = new Socket("127.0.0.1", 61666);

        DataOutputStream outputStream = new DataOutputStream(serverSocket.getOutputStream());
        DataInputStream inputStream = new DataInputStream(serverSocket.getInputStream());

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for(int i=0; i<1000000; i++){
            outputStream.writeUTF("");
            String inputString;
            if((inputString = inputStream.readUTF()) != null){
             System.out.println(inputString);
            }
        }

        stopWatch.stop();

        System.out.println("SocketTime: " + stopWatch.getTotalTimeMillis());
    }
}
