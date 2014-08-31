package design.patterns.observer;

/**
 * Created by Feng on 31/8/14.
 */
public class ObserverApp {

    public static void main(String[] args){

        Publisher publisher = new NewsPublisher();

        Subcriber subcriberA = new SubscriberA();
        Subcriber subcriberB = new SubscriberB();

        publisher.register(subcriberA);
        publisher.register(subcriberB);

        publisher.addNewUpdates("I have published a new update");
    }
}
