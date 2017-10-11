package auth;

import classes.Learner;

/**
 * Created by nkm on 11.10.2017.
 */
public interface LearnerDAO {
    Learner getLearner(String login);

    boolean updateLearner(Learner learner);
}
