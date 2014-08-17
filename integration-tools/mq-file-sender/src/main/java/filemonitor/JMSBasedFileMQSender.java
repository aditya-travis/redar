package filemonitor;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by meng on 6/10/14.
 */
public class JMSBasedFileMQSender {

    private MessageChannel fileCreationChannel;

    public JMSBasedFileMQSender(MessageChannel fileCreationChannel) {
        this.fileCreationChannel = fileCreationChannel;
    }

    private void prepareFiles() {

        for(int i=0; i< 100; i++){
            final int currentId = i + 1;

            fileCreationChannel.send(new Message<Object>() {
                @Override
                public MessageHeaders getHeaders() {
                    Map<String, Object> headerMap = new HashMap<String, Object>();
                    headerMap.put("fileId", String.valueOf(currentId));
                    return new MessageHeaders(headerMap);
                }

                @Override
                public Object getPayload() {
                    try {
                        byte[] fileContent = IOUtils.toByteArray(FileUtils.openInputStream(new File("/apps/redar/index.jpg")));
                        return fileContent;
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("Cannot open file",e);
                    }
                }
            });

            System.out.println("Done preparation for File [" + currentId + "]");
        }
    }

    public static void main(String[] args) throws Exception{
        FileUtils.cleanDirectory(new File("/apps/redar/dir/"));
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("file-mq-sending.xml");

        JMSBasedFileMQSender fileMQSender = applicationContext.getBean("fileMQSender", JMSBasedFileMQSender.class);

        fileMQSender.prepareFiles();
    }


}
