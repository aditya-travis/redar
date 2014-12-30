package build.ivy;
import org.apache.commons.lang.StringUtils;
/**
 * Created by mengf on 12/30/2014.
 */
public class HelloWorld {

    //static Logger logger = Logger.getLogger(HelloWorld.class);
    public static void main(String[] args) {
        String string = StringUtils.upperCase("Hello World From Ivy");
        System.out.println(string);
    }
}
