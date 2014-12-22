package build.ant;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

/**
 * Created by mengf on 12/22/2014.
 */
public class HelloWorld {
    static Logger logger = Logger.getLogger(HelloWorld.class);
    public static void main(String[] args) {
        logger.info("Hello World");
    }
}
