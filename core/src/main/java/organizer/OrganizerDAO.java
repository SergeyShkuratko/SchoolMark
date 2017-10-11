package organizer;


import classes.*;

import java.io.File;
import java.util.List;

public interface OrganizerDAO {

    public List<TestEvent> getAllByStatusForTeacher(Teacher teacher, Status status);

    public List<Learner> getAllLearnerInClass(SchoolClass shoolClass);

    public boolean createLearnerTestForTestEvent(TestEvent testEvent);

    public List<File> getFilesForLearnerTest(LearnerTest learnerTest);

    public boolean setLearnerTestStatus(LearnerTest learnerTest, Status status);

    public boolean sendTestEvent(TestEvent testEvent);

}