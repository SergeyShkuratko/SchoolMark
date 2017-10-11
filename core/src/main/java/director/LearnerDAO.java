package director;

import classes.Learner;
import classes.TestEvent;

import java.util.List;

/**
 * Created by nkm on 11.10.2017.
 */
public interface LearnerDAO {

    List<Learner> getLearnersWithMarksByTest(TestEvent test);  //или добавить еще MarksDAO и там тянуть оценки по каждому студенту?
}
