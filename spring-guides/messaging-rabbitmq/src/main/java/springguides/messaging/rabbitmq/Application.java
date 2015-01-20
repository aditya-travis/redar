package springguides.messaging.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

/**
 * Created by mengf on 1/20/2015.
 */
@Configuration
@EnableAutoConfiguration
public class Application{

    final static String queueName = "spring-boot";
    final static String exchangeName = "Spring-boot-exchange";

    @Bean
    Queue queue(){
        return new Queue(queueName);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(exchangeName);

    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange){

        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }

    @Bean
    MessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver){
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    Receiver receiver(CountDownLatch countDownLatch){
        return new Receiver(countDownLatch);
    }

    @Bean
    CountDownLatch countDownLatch(){
        return new CountDownLatch(1);
    }

    /*@Bean
    ConnectionFactory connectionFactory(){
        return new CachingConnectionFactory();
    }*/


    public static void main(String[] args) throws InterruptedException {
        ApplicationContext applicationContext =  SpringApplication.run(Application.class, args);

        RabbitTemplate rabbitTemplate = new RabbitTemplate(applicationContext.getBean(ConnectionFactory.class));
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(queueName, "Hello from rabbitMQ");

        applicationContext.getBean(CountDownLatch.class).await();
        System.exit(0);

    }

}
