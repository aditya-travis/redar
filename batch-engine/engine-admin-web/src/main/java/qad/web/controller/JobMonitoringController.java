package qad.web.controller;

import qad.history.model.JobHistoryEntry;
import qad.history.service.JobHistoryService;
import qad.admin.JobAdminService;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by meng on 6/19/14.
 */
@Controller
public class JobMonitoringController {
    private Logger logger = LoggerFactory.getLogger(JobMonitoringController.class);

    @Resource
    private JobHistoryService jobHistoryService;

    @Resource
    private JobAdminService jobAdminService;

    @RequestMapping(value = "/qad/history", method = RequestMethod.GET)
    @ResponseBody
    public PageImpl<JobHistoryEntry> listAllHistory(@PageableDefaults final Pageable pageable){
        if(null == pageable){

            return new PageImpl<JobHistoryEntry>(jobHistoryService.listAllHistory());
        }else{
            logger.debug("Received Pageable: {}", pageable);
            return new PageImpl<JobHistoryEntry>(jobHistoryService.listAllHistory());
        }
    }

    @RequestMapping(value = "/qad/jobs", method = RequestMethod.GET)
    @ResponseBody
    public List<JobKey> listAllJobs() throws Exception{
        return jobAdminService.listAllJobs();
    }

    @RequestMapping(value = "/triggers", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<Trigger>> listAllTriggers() throws Exception{
        return jobAdminService.listAllTriggers();
    }

    @RequestMapping(value = "/force-exec/{schedulderName}/{jobGroup}/{jobName}", method = RequestMethod.POST)
    @ResponseBody
    public void forceExecuteJob(@PathVariable("schedulderName") final String schedulderName,
                                @PathVariable("jobGroup") final String jobGroup,
                                @PathVariable("jobName") final String jobName) throws Exception{
        jobAdminService.forceExecute(schedulderName, jobGroup, jobName);
    }

}
