package filemonitor;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by meng on 6/11/14.
 */
public class SpringIntBasedFileReceiver implements FileReceiver {
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void receive(Message message) throws Exception{
        try {
            BytesMessage bytesMessage = (BytesMessage)message;
            byte[] input = new byte[(int)bytesMessage.getBodyLength()];
            //byte[] input = new byte[100];
            bytesMessage.readBytes(input);
            String fileName = bytesMessage.getStringProperty("fileName");

            FileUtils.writeByteArrayToFile(new File("/apps/redar/received/" + fileName ), input);
            System.out.println("["+ new Date() + "]" + "Received: " + fileName);
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        FileUtils.cleanDirectory(new File("/apps/redar/received/"));
        new ClassPathXmlApplicationContext("file-mq-receiver.xml");

    }
}
