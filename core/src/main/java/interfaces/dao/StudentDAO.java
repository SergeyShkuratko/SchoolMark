package interfaces.dao;

import classes.SchoolClass;
import classes.Student;
import classes.Test;

import java.util.List;

/**
 * Created by nkm on 11.10.2017.
 */
public interface StudentDAO {

    Student getStudent(String login);

    boolean update(Student student);

    List<Student> getStudentsWithMarksByTest(Test test);  //или добавить еще MarksDAO и там тянуть оценки по каждому студенту?

    public List<Student> getAllStudentsInClass(SchoolClass schoolClass);
}
