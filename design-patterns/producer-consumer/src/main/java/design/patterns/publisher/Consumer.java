package design.patterns.publisher;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Feng on 2/9/14.
 */
public class Consumer implements Runnable{

    private PriorityBlockingQueue<Task> taskQueue;

    public Consumer(PriorityBlockingQueue<Task> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void consume(Task task){
        System.out.println("Consuming Task: " + task);
    }


    @Override
    public void run() {
        try {

            while(true){
                consume(taskQueue.take());
                Thread.sleep(3000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
