package control;

/**
 * Created by meng on 6/5/14.
 */
public interface PassControlService {

    public boolean accquirePass(String userId) throws Exception;

    public void releasePass(String userId) throws Exception;

    public void dispayCurrentStatus();
}
