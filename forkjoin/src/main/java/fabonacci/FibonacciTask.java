package fabonacci;

import java.util.concurrent.RecursiveTask;

/**
 * Created by Feng on 27/4/14.
 */
public class FibonacciTask extends RecursiveTask<Long> {
    private FibonacciProblem fibonacciProblem;

    private static final int THRESHOLD=20;

    private long result;

    public FibonacciTask(FibonacciProblem fibonacciProblem) {
        this.fibonacciProblem = fibonacciProblem;
    }

    @Override
    protected Long compute() {
        if(fibonacciProblem.n <= THRESHOLD){
            return fibonacciProblem.solve();
        }else{
            FibonacciTask worker1 = new FibonacciTask(new FibonacciProblem(fibonacciProblem.n-1));
            FibonacciTask worker2 = new FibonacciTask(new FibonacciProblem(fibonacciProblem.n-2));
            worker1.fork();
            result = worker2.compute() + worker1.join();
            return result;
        }
    }

    public long getResult() {
        return result;
    }
}
