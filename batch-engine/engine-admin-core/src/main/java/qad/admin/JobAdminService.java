package qad.admin;

import org.quartz.JobKey;
import org.quartz.Trigger;

import java.util.List;
import java.util.Map;

/**
 * Created by meng on 6/16/14.
 */
public interface JobAdminService {

    public List<JobKey> listAllJobs() throws Exception;

    List<JobKey> listAllJobs(String schedulerName) throws Exception;

    Map<String, List<Trigger>> listAllTriggers() throws Exception;

    List<Trigger> listAllTriggers(String schedulerName) throws Exception;

    boolean forceExecute(String schedulerName, String jobName, String jobGroupName);
}
