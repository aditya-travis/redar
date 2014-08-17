package qad.history.dao;

import qad.history.model.JobHistoryEntry;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * Created by meng on 6/18/14.
 */
public class MybatisJobHistoryDao extends SqlSessionDaoSupport implements JobHistoryDao{

    @Override
    public List<JobHistoryEntry> findAll() {
        return getSqlSession().selectList(getMapper() + ".selectAll");
    }

    protected String getMapper(){
        return "history.dao.JobHistoryMapper";
    }
}
