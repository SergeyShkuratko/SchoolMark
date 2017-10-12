package interfaces.dao;

import classes.Status;
import classes.Student;
import classes.Test;
import classes.Work;

import java.util.List;

public interface WorkDAO {
    /**
     * Создает записи Work для всех учеников из test.schoolClass
     * @param test - контрольная работа
     * @return - признак успешного завершения работы операции
     */
    public boolean addWorksForTest(Test test);

    public boolean updateWork(Work work);

    public List<Work> getAllByStudent(Student student);
}
