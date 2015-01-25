package springguides.messaging.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.FileSystemUtils;

import java.io.File;

/**
 * Created by meng on 1/25/15.
 */
public class Receiver {
    //Get the application context
    @Autowired
    ConfigurableApplicationContext applicationContext;

    public void receiveMessage(String message){
        System.out.println("Received <" + message + ">");
        applicationContext.close();
        FileSystemUtils.deleteRecursively(new File("activemq-data"));
    }
}
