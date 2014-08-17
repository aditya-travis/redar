import model.ProcessContextHolder;
import service.ProcessingService;
import service.ProcessingServiceImpl;

/**
 * Created by meng on 6/3/14.
 */
public class NonThreadPooledProcessingDemo {

    public static void main(String... args){

        for(int i=0; i<=5; i++){
            final int idx = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ProcessContextHolder.getProcessContext().setProcessingId("Process-" + idx);
                    ProcessContextHolder.getProcessContext().setProcessor("Processor-" + idx);

                    ProcessingService processingService = new ProcessingServiceImpl();

                    processingService.Process();

                    ProcessContextHolder.destroyProcessContext();
                }
            }).start();

        }
    }
}
