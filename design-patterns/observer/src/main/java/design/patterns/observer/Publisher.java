package design.patterns.observer;

/**
 * Created by Feng on 31/8/14.
 */
public interface Publisher {

    void register(Subcriber subcriber);
    void deregister(Subcriber subcriber);
    void notifySubscribers();
    String getUpdates();
    void addNewUpdates(String updates);
}
