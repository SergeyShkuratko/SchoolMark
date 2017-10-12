package interfaces.dao;

import classes.Teacher;
import classes.TestTemplate;

import java.util.List;

public interface TestEventTemplateDAO {
    int save(TestTemplate template);
    boolean update(TestTemplate template);
    boolean delete(TestTemplate template);
    List<TestTemplate> getByOwner(Teacher owner);
    List<TestTemplate> getAll();
}
