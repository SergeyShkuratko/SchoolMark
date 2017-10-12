package interfaces;

import classes.Work;
import classes.Teacher;
import classes.Test;

import java.util.List;


//TODO спорный вопрос насчет package. Возможно это DAO
public interface TestCheck {

    /**
     * Get all unchecked tests for specified teacher
     */
    List<Test> getUncheckedTest(Teacher teacher);

    /**
     * Get all unchecked works for specified teacher
     *
     * @param teacher
     * @return list of Works
     */
    List<Work> getUncheckedWorks(Teacher teacher, int testId);

    /**
     * Set mark for one student's work
     *
     * @param work
     * @return true if success, false if error while writing to database
     */
    boolean setMark(Work work);

}