package springguides.messaging.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * Created by mengf on 1/20/2015.
 */
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch countDownLatch;

    public Receiver(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void receiveMessage(String message){
        logger.info("received <" + message + ">");
        countDownLatch.countDown();
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }
}
