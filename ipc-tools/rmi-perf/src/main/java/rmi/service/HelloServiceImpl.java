package rmi.service;

/**
 * Created by Feng on 31/5/14.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello() {
        //System.out.println("This is Hello. ");
        return "This is Hello";
    }
}
