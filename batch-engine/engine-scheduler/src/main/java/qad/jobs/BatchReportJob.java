package qad.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by meng on 6/16/14.
 */
public class BatchReportJob extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(BatchReportJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("Started BatchReportJob with Job Context {}", jobExecutionContext);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Finished Running BatchReportJob.");
    }
}
