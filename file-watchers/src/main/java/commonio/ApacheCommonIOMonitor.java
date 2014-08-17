package commonio;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by meng on 6/15/14.
 */
public class ApacheCommonIOMonitor {

    public static void main(String[] args) throws Exception{
        final String FOLDER = "/apps/redar/dir";
        final long pollInterval = 1;
        File folder = new File(FOLDER);

        final AtomicInteger eventId = new AtomicInteger(0);

        FileAlterationObserver fileAlterationObserver = new FileAlterationObserver(folder);
        FileAlterationMonitor fileAlterationMonitor = new FileAlterationMonitor(pollInterval);
        FileAlterationListener fileAlterationListener = new FileAlterationListenerAdaptor(){
            @Override
            public void onFileCreate(File file) {
                System.out.println("["+eventId.incrementAndGet()+"]File Event[ENTRY_CREATE] is detected: " + file.getAbsolutePath());
            }

            @Override
            public void onFileChange(File file) {
                System.out.println("["+eventId.incrementAndGet()+"]File Event[ENTRY_MODIFIED] is detected: " + file.getAbsolutePath());
            }

            @Override
            public void onFileDelete(File file) {
                System.out.println("["+eventId.incrementAndGet()+"]File Event[ENTRY_DELETE] is detected: " + file.getAbsolutePath());
            }



        };

        fileAlterationObserver.addListener(fileAlterationListener);
        fileAlterationMonitor.addObserver(fileAlterationObserver);
        fileAlterationMonitor.start();
    }
}
