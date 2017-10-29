package interfaces.dao;

import classes.SchoolClass;
import classes.Student;
import classes.Test;

import java.util.List;

public interface StudentDAO {

    Student getStudent(String login);
    Student getStudent(int id);

    boolean update(Student student);

    List<Student> getStudentsWithMarksByTest(Test test);  //или добавить еще MarksDAO и там тянуть оценки по каждому студенту?

    public List<Student> getAllStudentsInClass(SchoolClass schoolClass);
}
