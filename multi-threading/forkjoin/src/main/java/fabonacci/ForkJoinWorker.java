package fabonacci;

import org.perf4j.StopWatch;

import java.util.concurrent.ForkJoinPool;

/**
 * Created by Feng on 27/4/14.
 */
public class ForkJoinWorker {

    public static void main(String[] args){
        //Check the number of processor in this computer
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of Processors:  " + processors);

        int n=50;
        StopWatch stopWatch = new StopWatch();
        FibonacciProblem  fibonacciProblem = new FibonacciProblem(n);
        ForkJoinPool pool = new ForkJoinPool(processors);

        FibonacciTask  fibonacciTask = new FibonacciTask(fibonacciProblem);

        pool.invoke(fibonacciTask);

        long result = fibonacciTask.getResult();
        stopWatch.stop();

        System.out.println("Computed Result: " + result);
        System.out.println("ElapseTime: " + stopWatch.getElapsedTime());
    }
}
