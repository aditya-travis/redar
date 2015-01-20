package spingguides.messaging.redis;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

/**
 * Created by mengf on 1/20/2015.
 */
@Configuration
@EnableAutoConfiguration
public class Application {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(Application.class);

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver){
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    CountDownLatch countDownLatch(){
        return new CountDownLatch(1);
    }

    @Bean
    Receiver receiver(CountDownLatch countDownLatch){
        return new Receiver(countDownLatch);
    }

    @Bean
    StringRedisTemplate redisTemplate(RedisConnectionFactory connectionFactory){
        return new StringRedisTemplate(connectionFactory);
    }

    @Bean
    RedisConnectionFactory connectionFactory(){
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();

        return connectionFactory;

    }

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

        StringRedisTemplate redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
        CountDownLatch countDownLatch = applicationContext.getBean(CountDownLatch.class);

        logger.info("Sending Message....");
        redisTemplate.convertAndSend("chat", "Hello Redis!");

        countDownLatch.await();

        System.exit(0);

    }

}
