package controller;

import classes.LearnerTest;
import classes.Teacher;
import classes.TestEvent;

import java.util.List;

public interface TestCheck {

    /**
     * Get all unchecked tests for specified teacher
     */
    List<TestEvent> getUncheckedTest(Teacher teacher);

    /**
     * Get all unchecked works for specified teacher
     *
     * @param teacher
     * @return list of Works
     */
    List<LearnerTest> getUncheckedWorks(Teacher teacher, int testId);

    /**
     * Set mark for one student's work
     *
     * @param work
     * @return true if success, false if error while writing to database
     */
    boolean setMark(LearnerTest work);

}