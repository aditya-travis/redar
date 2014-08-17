package filemonitor;

import javax.jms.Message;
import java.io.File;

/**
 * Created by meng on 6/11/14.
 */
public interface FileReceiver {

    public void receive(Message message) throws Exception;
}
