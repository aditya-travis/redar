package rmi.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StopWatch;
import rmi.service.HelloService;

/**
 * Created by Feng on 31/5/14.
 */
public class HelloServiceClient {

    private HelloService helloRemoteService;
    private HelloService helloLocalService;

    public HelloServiceClient(HelloService helloRemoteService, HelloService helloLocalService) {
        this.helloRemoteService = helloRemoteService;
        this.helloLocalService = helloLocalService;
    }

    public HelloService getHelloRemoteService() {
        return helloRemoteService;
    }

    public void setHelloRemoteService(HelloService helloRemoteService) {
        this.helloRemoteService = helloRemoteService;
    }

    public HelloService getHelloLocalService() {
        return helloLocalService;
    }

    public void setHelloLocalService(HelloService helloLocalService) {
        this.helloLocalService = helloLocalService;
    }

    public static void main(String... args){


        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("rmi-client.xml");
        HelloServiceClient client = applicationContext.getBean("helloServiceClient", HelloServiceClient.class);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for(int i=0; i<1000000; i++){
            System.out.println(client.getHelloRemoteService().sayHello());
        }

        stopWatch.stop();

        long remoteTime = stopWatch.getTotalTimeMillis();
        StopWatch stopWatch1 = new StopWatch();
        stopWatch1.start();

        for(int i=0; i<1000000; i++){
            System.out.println(client.getHelloLocalService().sayHello());
        }

        stopWatch1.stop();

        long localtime = stopWatch1.getTotalTimeMillis();
        System.out.println("RemoteTime: " + remoteTime + " Localtime: " + localtime);
    }
}
