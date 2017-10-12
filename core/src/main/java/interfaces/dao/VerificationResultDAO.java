package interfaces.dao;

import classes.VerificationResult;
import classes.Work;

import java.util.List;

public interface VerificationResultDAO {

    List<VerificationResult> getAllForWork(Work work);
    //TODO: Не забыть добавить метод добавления!

}
