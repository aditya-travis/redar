package qad.history.service;

import qad.history.dao.JobHistoryDao;
import qad.history.model.JobHistoryEntry;

import java.util.List;

/**
 * Created by meng on 6/18/14.
 */
public class QuartzJobHistoryService implements JobHistoryService {
    private JobHistoryDao jobHistoryDao;

    public QuartzJobHistoryService(JobHistoryDao jobHistoryDao) {
        this.jobHistoryDao = jobHistoryDao;
    }

    @Override
    public List<JobHistoryEntry> listAllHistory() {
        return jobHistoryDao.findAll();
    }
}
