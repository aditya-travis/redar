package qad.plugin;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.listeners.JobListenerSupport;
import org.quartz.spi.ClassLoadHelper;
import org.quartz.spi.SchedulerPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by meng on 6/17/14.
 */
public class DBPersistentJobHistoryPlugin extends JobListenerSupport implements SchedulerPlugin {

    private Logger logger = LoggerFactory.getLogger(this.getName());

    private static final String applicationContextSchedulerContextKey = "applicationContext";
    
    private static final String INSERT_JOB_HISTORY = 
            "INSERT INTO QRTZ_JOB_HISTORY " +
            "( " +
            "  ENTRY_ID, HOSTNAME, IP_ADDR, SCHED_NAME, INSTANCE_NAME, JOB_NAME, JOB_GROUP, TRIGGER_NAME, TRIGGER_GROUP, " +
            "  FIRED_TIME, NEXT_FIRE_TIME, PRIORITY, IS_NONCONCURRENT, REQUESTS_RECOVERY, RESULT, EXCEPTIONS " +
            ") " +
            "VALUES " +
            "  ( " +
            "    QRTZ_JOB_HISTORY_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? " +
            "  );";

    private String jdbcTemplateBeanName = "jdbcTemplate";

    @Override
    public void initialize(String s, Scheduler scheduler, ClassLoadHelper classLoadHelper) throws SchedulerException {
        scheduler.getListenerManager().addJobListener(this, EverythingMatcher.allJobs());
        logger.info("{} is initialized..", getName());
    }

    @Override
    public void start() {
        logger.info("{} is started..", getName());
    }

    @Override
    public void shutdown() {
        logger.info("{} is shutdown..", getName());
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        JdbcTemplate jdbcTemplate = getJdbcTemplate(context);

        try {
            jdbcTemplate.update(INSERT_JOB_HISTORY, new Object[]{
                    getCurrentHostName(), getCurrentIPAddr(),
                    context.getScheduler().getSchedulerName(), context.getScheduler().getSchedulerInstanceId(),
                    context.getJobDetail().getKey().getName(), context.getJobDetail().getKey().getGroup(),
                    context.getTrigger().getKey().getName(), context.getTrigger().getKey().getGroup(),
                    context.getFireTime(), context.getTrigger().getNextFireTime(), context.getTrigger().getPriority(),
                    true,
                    context.isRecovering(),
                    getJobExecutionResult(context, jobException),
                    getJobExecutionResultMessage(context, jobException)

            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to persist the job history", e);
        }
    }

    private String getJobExecutionResultMessage(JobExecutionContext context, JobExecutionException jobException) {
        return null == jobException ? "": ExceptionUtils.getStackTrace(jobException);
    }

    private String getJobExecutionResult(JobExecutionContext context, JobExecutionException jobException) {
        return null == jobException ? "SUCCESS": "FAIL";
    }

    private String getCurrentIPAddr() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "UNKNOWN_IP";
        }

    }

    private String getCurrentHostName(){
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "UNKNOWN_HOST";
        }
    }



    private JdbcTemplate getJdbcTemplate(JobExecutionContext context) {
        try {
            return ((ApplicationContext)context.getScheduler().getContext().get(applicationContextSchedulerContextKey)).getBean(this.jdbcTemplateBeanName, JdbcTemplate.class);
        } catch (SchedulerException e) {
            throw new RuntimeException("Unable to get the JdbcTemplate bean with id="+ this.jdbcTemplateBeanName,e);
        }
    }
}
