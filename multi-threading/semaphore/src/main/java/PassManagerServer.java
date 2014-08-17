import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by meng on 6/5/14.
 */
public class PassManagerServer {

    public static void main(String... args){
        new ClassPathXmlApplicationContext("passmanager-server.xml");
        System.out.println("PassManager Server started");
    }
}
