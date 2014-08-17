package control;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

/**
 * Created by meng on 6/5/14.
 */
public class PassManager implements PassControlService{

    private Semaphore systemPermits = new Semaphore(4, true);

    private Map<String, Semaphore> userPermits = new ConcurrentHashMap<String, Semaphore>();

    private static PassManager passManager = new PassManager();
    public PassManager() {
        userPermits.put("user1", new Semaphore(1, true));
        userPermits.put("user2", new Semaphore(1, true));
        userPermits.put("user3", new Semaphore(1, true));
        userPermits.put("user4", new Semaphore(1, true));
    }

    public static PassManager getInstance(){
        return passManager;
    }

    public boolean accquirePass(String userId) throws Exception{
        systemPermits.acquire(); //blocks the call
        System.out.println(Thread.currentThread().getName() + " Got System pass, waiting for User pass: " + userId);
        userPermits.get(userId).acquire(); //blocks the call
        System.out.println(Thread.currentThread().getName() + " Got System pass And User pass: " + userId);
        return true;
    }

    public void releasePass(String userId) throws Exception{
        systemPermits.release();
        userPermits.get(userId).release();
        dispayCurrentStatus();
    }

    public void dispayCurrentStatus(){
        String userPermistStatus = "";
        for(Map.Entry<String, Semaphore> entry : userPermits.entrySet()){
            userPermistStatus += entry.getKey() + ": [Permits: " + entry.getValue().availablePermits() + "]\n";
        }
        System.out.println("SystemPermits: [Permits: " + systemPermits.availablePermits() + " ]\n"
        +"UserPermists:" + userPermistStatus);
    }
}
