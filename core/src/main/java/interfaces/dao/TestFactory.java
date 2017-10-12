package interfaces.dao;

import classes.*;

import java.time.LocalDate;
import java.util.List;

public interface TestFactory {
    Test createFromTemplate(TestTemplate template);
    List<Test> getByOrganizer(Teacher user);
    List<Test> getByDate(LocalDate date);
    List<Test> getByVerifier(Teacher verifier);
}
