package fabonacci;

import org.perf4j.StopWatch;

/**
 * Created by Feng on 27/4/14.
 */
public class SillyWorker {

    public static void main(String[] args){
        int n=50;
        StopWatch stopWatch = new StopWatch();
        FibonacciProblem fibonacciProblem = new FibonacciProblem(n);

        System.out.println("Computing Fib Number: " + n);
        long result = fibonacciProblem.solve();
        stopWatch.stop();
        System.out.println("Computed result: " + result);

        System.out.println("Elaspsed time (in milli): " + stopWatch.getElapsedTime());


    }

}
