import model.ProcessContext;
import model.ProcessContextHolder;
import service.ProcessingService;
import service.ProcessingServiceImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by meng on 6/3/14.
 */
public class ThreadPooledProcessingDemo {

    public static void main(String... args){

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for(int i=0; i<10; i++){
            final int idx = i+1;
            executorService.execute(new Runnable() {
                @Override
                public void run() {

                    ProcessContext processContext = new ProcessContext(){{
                        setProcessingId("Process-" + idx);
                        setProcessor("Processor-" + idx);
                    }};

                    ProcessContextHolder.setProcessContext(processContext);

                    ProcessingService processingService = new ProcessingServiceImpl();

                    processingService.Process();

                    ProcessContextHolder.destroyProcessContext();
                }
            });

        }

        executorService.shutdown();
    }
}
