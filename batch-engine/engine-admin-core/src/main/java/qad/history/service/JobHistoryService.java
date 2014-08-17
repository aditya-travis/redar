package qad.history.service;

import qad.history.model.JobHistoryEntry;

import java.util.List;

/**
 * Created by meng on 6/18/14.
 */
public interface JobHistoryService {

    public List<JobHistoryEntry> listAllHistory();
}
