package build.ant;

import org.junit.Test;

import static org.junit.Assert.*;

public class HelloWorldTest {

    @Test
    public void testMain(){
        HelloWorld.main(new String[]{});
        System.out.println("Done running " + this.getClass().getName());
    }
}