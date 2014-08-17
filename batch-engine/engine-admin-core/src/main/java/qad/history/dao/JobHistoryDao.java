package qad.history.dao;

import qad.history.model.JobHistoryEntry;

import java.util.List;

/**
 * Created by meng on 6/18/14.
 */
public interface JobHistoryDao {

    public List<JobHistoryEntry> findAll();
}
