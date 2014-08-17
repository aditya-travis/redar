package fabonacci;

/**
 * Created by Feng on 27/4/14.
 */
public class FibonacciProblem {

    int n;

    public FibonacciProblem(int n) {
        this.n = n;
    }

    public long solve(){
        return fabonacci(n);
    }

    private long fabonacci(int n) {
        System.out.println("Thread: " + Thread.currentThread().getName() + " calculate " + n);

        if(n<=1){
            return n;
        }else{
            return fabonacci(n-1) + fabonacci(n-2);
        }
    }
}
