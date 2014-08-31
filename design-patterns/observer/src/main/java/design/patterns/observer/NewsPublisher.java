package design.patterns.observer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Feng on 31/8/14.
 */
public class NewsPublisher implements Publisher {

    private List<Subcriber> subcribers = new CopyOnWriteArrayList<Subcriber>();

    private List<String> news = new CopyOnWriteArrayList<String>();

    @Override
    public void register(Subcriber subcriber) {
        subcribers.add(subcriber);
    }

    @Override
    public void deregister(Subcriber subcriber) {
        subcribers.remove(subcriber);
    }

    @Override
    public void notifySubscribers() {
        for(Subcriber subcriber : subcribers){
            subcriber.update(this);
        }
    }

    @Override
    public String getUpdates() {
        return news.get(news.size() - 1);
    }

    @Override
    public void addNewUpdates(String updates) {
        news.add(updates);
        notifySubscribers();
    }
}
