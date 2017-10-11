package interfaces.dao;

import classes.Teacher;
import classes.TestEvent;
import classes.TestEventTemplate;

import java.time.LocalDate;
import java.util.List;

public interface TestEventFactory {
    TestEvent createFromTemplate(TestEventTemplate template);
    List<TestEvent> getByOrganizer(Teacher user);
    List<TestEvent> getByDate(LocalDate date);
    List<TestEvent> getByExaminer(Teacher examiner);
}
