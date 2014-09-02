package design.patterns.publisher;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Feng on 2/9/14.
 */
public class Producer {

    private PriorityBlockingQueue<Task> taskQueue;

    public Producer(PriorityBlockingQueue<Task> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void produce(Task task){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        taskQueue.add(task);
    }
}
