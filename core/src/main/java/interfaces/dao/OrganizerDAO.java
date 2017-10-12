package interfaces.dao;


import classes.*;

import java.io.File;
import java.util.List;

public interface OrganizerDAO {

    public List<Test> getAllByStatusForTeacher(Teacher teacher, Status status);

    public boolean sendTest(Test test);

}