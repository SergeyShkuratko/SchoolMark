package director;

import classes.Learner;
import classes.Test;

import java.util.List;

/**
 * Created by nkm on 11.10.2017.
 */
public interface LearnerDAO {

    List<Learner> getLearnersWithMarksByTest(Test test);  //или добавить еще MarksDAO и там тянуть оценки по каждому студенту?
}
