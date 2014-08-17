
import control.PassControlService;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by meng on 6/5/14.
 */
public class Processor2 {

    public static void main(String[] args){
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("processor2-context.xml");
        final PassControlService passManager = applicationContext.getBean("passManager", PassControlService.class);

        ExecutorService executorService = Executors.newFixedThreadPool(20, new BasicThreadFactory.Builder().daemon(false).namingPattern("Processor1-%02d").build());

        for(int i=0; i<20; i++){

            final int currentId = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    String userId = "";
                    if(currentId <= 2){
                        userId = "user1";
                    }else if(currentId <=6){
                        userId ="user2";
                    }else if(currentId <=12){
                        userId = "user3";
                    }else if(currentId <=20){
                        userId = "user4";
                    }

                    try {
                        passManager.accquirePass(userId);
                        System.out.println(Thread.currentThread().getName() + " is processing " + userId);
                        passManager.dispayCurrentStatus();
                        Thread.sleep(3000);
                        passManager.releasePass(userId);
                        System.out.println(Thread.currentThread().getName() + " released all passes: " + userId);
                        passManager.dispayCurrentStatus();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }


        executorService.shutdown();
        applicationContext.destroy();
    }
}
