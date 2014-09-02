package design.patterns.publisher;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Feng on 2/9/14.
 */
public class PriorityTaskApp {

    public static void main(String[] args){
        PriorityBlockingQueue<Task> taskQueue = new PriorityBlockingQueue<Task>(30, new Task.TaskComparator());

        Producer producer = new Producer(taskQueue);

        new Thread(new Consumer(taskQueue), "Consumer-1").start();
        //new Thread(new Consumer(taskQueue), "Consumer-2").start();

        producer.produce(new Task(Task.TaskType.SELL, "SELL0001", "SELLING"));
        producer.produce(new Task(Task.TaskType.CANCEL, "CXL0001", "CANCELLING"));
        producer.produce(new Task(Task.TaskType.BUY, "BUY0001", "BUYING"));
        producer.produce(new Task(Task.TaskType.SELL, "SELL0002", "SELLING"));
        producer.produce(new Task(Task.TaskType.CANCEL, "CXL0002", "CANCELLING"));
        producer.produce(new Task(Task.TaskType.BUY, "BUY0002", "BUYING"));



    }
}
