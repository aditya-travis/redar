package filemonitor;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by meng on 6/10/14.
 */
public class JMSbasedFileProcessor implements FileProcessor {

    private JmsTemplate jmsTemplate;

    public JMSbasedFileProcessor(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void process(final File input) throws Exception{
        System.out.println("["+ new Date() + "]" + "Processing" + input.getName());

        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {

                try {

                    BytesMessage bytesMessage = session.createBytesMessage();
                    bytesMessage.setStringProperty("fileName", input.getName());
                    bytesMessage.writeBytes(IOUtils.toByteArray(FileUtils.openInputStream(input)));
                    return bytesMessage;

                } catch (IOException e) {
                    throw new RuntimeException("Cannot construct bytes Message. ", e);
                }
            }
        });
    }
}
