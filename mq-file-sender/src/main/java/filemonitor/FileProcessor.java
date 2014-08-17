package filemonitor;

import java.io.File;

/**
 * Created by meng on 6/10/14.
 */
public interface FileProcessor {

    public void process(File input) throws Exception;
}
