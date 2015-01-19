package springguides.schedulingtasks;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by mengf on 1/19/2015.
 */
@EnableScheduling
@ComponentScan
public class Application
{
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
