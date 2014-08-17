package org.zths.samples.router;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Feng on 24/5/14.
 */
public class MQRouterDemo {

    public static void main(String... args){
        new ClassPathXmlApplicationContext("classpath:applicationContext-mq-router.xml");
    }
}
