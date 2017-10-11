package interfaces.dao;

import classes.Teacher;
import classes.TestEventTemplate;

import java.util.List;

public interface TestEventTemplateDAO {
    int save(TestEventTemplate template);
    boolean update(TestEventTemplate template);
    boolean delete(TestEventTemplate template);
    List<TestEventTemplate> getByOrganizer(Teacher user);
    List<TestEventTemplate> getAll();
}
