package design.patterns.observer;

/**
 * Created by Feng on 31/8/14.
 */
public class SubscriberB implements Subcriber {

    @Override
    public void update(Publisher publisher) {
        System.out.println("Subscriber B Received " + publisher.getUpdates());
    }
}
