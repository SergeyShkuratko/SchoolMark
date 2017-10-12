package interfaces.dao;

import classes.Work;
import classes.WorkPage;

import java.util.List;

public interface WorkPageDAO {

    public WorkPage getWorkPageById(long id);

    public boolean removeWorkPage(WorkPage workPage);

    public List<WorkPage> getWorkPagesForWork(Work work);

    public boolean addWorkPage(WorkPage workPage);



}
