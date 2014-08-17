package rmi.register;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Feng on 31/5/14.
 */
public class RMIServerRegister {

    public static void main(String... args){
        new ClassPathXmlApplicationContext("rmi-setup.xml");
        System.out.println("RMI Server started");
    }
}
