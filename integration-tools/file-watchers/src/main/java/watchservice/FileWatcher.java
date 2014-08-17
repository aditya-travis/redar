package watchservice;

import java.nio.file.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by meng on 6/15/14.
 */
public class FileWatcher {

    public static void main(String[] args) throws Exception{

        final WatchService watchService = FileSystems.getDefault().newWatchService();
        final Path pathToWatch = FileSystems.getDefault().getPath("/apps/redar/dir");
        pathToWatch.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);

        ExecutorService executorService = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(20000), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("Runnable is getting rejected.. running it in the main thread");
                r.run();
            }
        });

        final AtomicInteger envetId = new AtomicInteger(0);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for(;;){
                    WatchKey watchKey = null;

                    try {
                        watchKey = watchService.take();

                        for(WatchEvent fileEnvent: watchKey.pollEvents()){
                            if(fileEnvent.kind() == StandardWatchEventKinds.OVERFLOW){
                                System.out.println("Lost the event");
                            }else{

                                int currentEventId = envetId.incrementAndGet();

                                WatchEvent<Path> filePathEvenet = (WatchEvent<Path>) fileEnvent;

                                Path currentFileName=filePathEvenet.context();

                                Path fullFilePath = pathToWatch.resolve(currentFileName);

                                System.out.println("["+currentEventId+"]File Event["+ fileEnvent.kind() +"] is detected: " + fullFilePath.toUri());

                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        watchKey.reset();
                    }


                }
            }
        });



    }
}
