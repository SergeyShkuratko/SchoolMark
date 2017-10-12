package interfaces.dao;


import classes.*;

import java.io.File;
import java.util.List;

public interface OrganizerDAO {

    public List<Test> getAllByStatusForTeacher(Teacher teacher, Status status);

    public List<Student> getAllStudentsInClass(SchoolClass shoolClass);

    public boolean createWorkForTest(Test test);

    public List<File> getFilesForWork(Work work);

    public boolean setWorkStatus(Work work, Status status);

    public boolean sendTest(Test test);

}