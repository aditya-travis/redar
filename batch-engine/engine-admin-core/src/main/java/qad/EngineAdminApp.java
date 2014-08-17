package qad;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import qad.admin.JobAdminService;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by meng on 6/16/14.
 */
public class EngineAdminApp {

    private static Logger logger = LoggerFactory.getLogger(EngineAdminApp.class);

    private static OptionParser optionParser;

    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/engine-admin-core.xml");
        JobAdminService jobAdminService = applicationContext.getBean("jobAdminService", JobAdminService.class);

        OptionSet optionSet = parseCmdLineOptions(args);

        if(optionSet.has("help")){
            optionParser.printHelpOn(System.out);
            closeApplication(applicationContext);
            return;
        }

        if(optionSet.has("execute") && optionSet.has("jobname") && optionSet.has("groupname")){
            String jobname = (String) optionSet.valueOf("jobname");
            String groupname = (String) optionSet.valueOf("groupname");

            System.out.println("\tForce Executing A Specified Job");
            System.out.println("-----------------------------------------------------------------");
            boolean result = jobAdminService.forceExecute("reportScheduler", jobname, groupname);
            if(result){
                System.out.println(groupname + "." + jobname + " is force executed..");
            }
            System.out.println("-----------------------------------------------------------------\n");
            closeApplication(applicationContext);
            return;
        }

        if(optionSet.has("list") && optionSet.has("jobs")){

            System.out.println("\tList All Jobs");
            System.out.println("-----------------------------------------------------------------");

            for(JobKey jobKey : jobAdminService.listAllJobs()){
                System.out.println(jobKey);
            }

            System.out.println("-----------------------------------------------------------------\n");
            closeApplication(applicationContext);
            return;
        }

    }

    public static void closeApplication(AbstractApplicationContext applicationContext){
        applicationContext.close();
    }

    private static OptionSet parseCmdLineOptions(String[] args) throws IOException {
        OptionParser optionParser = new OptionParser();

        optionParser.accepts("execute", "force execute option group").withOptionalArg().ofType(String.class);
        optionParser.accepts("jobname", "job name").withRequiredArg().ofType(String.class);
        optionParser.accepts("groupname", "job group name").withOptionalArg().ofType(String.class).defaultsTo("DEAULT");

        optionParser.accepts("jobs", "list jobs").withOptionalArg().ofType(String.class);
        optionParser.accepts("list", "list all").requiredIf("jobs").withOptionalArg().ofType(String.class);

        optionParser.accepts("help", "print help message").withOptionalArg().ofType(String.class);

        EngineAdminApp.optionParser = optionParser;
        return optionParser.parse(args);
    }
}
