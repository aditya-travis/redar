import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by meng on 6/16/14.
 */
public class EngineSchedulerApp {

    public static void main(String[] args){
        new ClassPathXmlApplicationContext("META-INF/spring/engine-scheduler.xml");
    }
}
