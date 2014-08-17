package zeromq.client;

import org.springframework.util.StopWatch;
import org.zeromq.ZMQ;

/**
 * Created by meng on 5/31/14.
 */
public class ZeroMQClient {

    public static void main(String[] args) {
        ZMQ.Context context = ZMQ.context(1);

        //  Socket to talk to server
        System.out.println("Connecting to hello world server…");

        ZMQ.Socket requester = context.socket(ZMQ.REQ);
        requester.connect("tcp://localhost:5555");

        StopWatch stopWatch = new StopWatch();

        //warm up
        for (int requestNbr = 0; requestNbr != 1000; requestNbr++) {
            String request = "";
            //System.out.println("Sending Hello " + requestNbr);
            requester.send(request.getBytes(), 0);

            byte[] reply = requester.recv(0);
            System.out.println(new String(reply));
        }

        stopWatch.start();

        for (int requestNbr = 0; requestNbr != 10000000; requestNbr++) {
            String request = "";
            //System.out.println("Sending Hello " + requestNbr);
            requester.send(request.getBytes(), 0);

            byte[] reply = requester.recv(0);
            System.out.println(new String(reply));
        }

        stopWatch.stop();

        System.out.println("ZeroMQ Time: " + stopWatch.getTotalTimeMillis());
        requester.close();
        context.term();
    }
}
