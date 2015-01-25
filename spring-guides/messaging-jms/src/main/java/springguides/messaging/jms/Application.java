package springguides.messaging.jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.util.FileSystemUtils;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.File;

/**
 * Created by meng on 1/25/15.
 */
@Configuration
@EnableAutoConfiguration
public class Application {
    private static final String mailboxDestination = "mailbox-destination";

    @Bean
    Receiver receiver(){
        return new Receiver();
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver){
        MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(receiver);
        listenerAdapter.setDefaultListenerMethod("receiveMessage");
        return listenerAdapter;
    }

    @Bean
    SimpleMessageListenerContainer listenerContainer(MessageListenerAdapter listener, ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
        listenerContainer.setConnectionFactory(connectionFactory);
        listenerContainer.setMessageListener(listener);
        listenerContainer.setDestinationName(mailboxDestination);
        return listenerContainer;
    }

    public static void main(String[] args) {
        //Clean out the activemq data from the previous run
        FileSystemUtils.deleteRecursively(new File("activemq-data"));
        //Lunch the application
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class);

        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);

        System.out.println("Sending a new message");
        jmsTemplate.send(mailboxDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("Ping");
            }
        });
    }
}
