package qad.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by meng on 7/5/14.
 */
public class PaymentProcessingJob extends QuartzJobBean{

    private Logger logger = LoggerFactory.getLogger(BatchReportJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        logger.info("Started Payment Processing with Job Context {}", jobExecutionContext);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Finished Running PaymentProcessingJob..");
    }
}
