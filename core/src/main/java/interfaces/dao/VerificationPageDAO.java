package interfaces.dao;

import classes.VerificationPage;
import classes.Work;

import java.util.List;

public interface VerificationPageDAO {

    public List<VerificationPage> getVerificationPagesForWork(Work work);

    //TODO: не забыть добавить методы добавления и удаления!
}
