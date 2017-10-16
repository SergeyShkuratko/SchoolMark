package DAOImplementation;

import classes.*;
import connectionmanager.ConnectionManager;
import connectionmanager.ConnectionManagerPostgresImpl;
import interfaces.dao.TestDAO;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class TestDAOImpl implements TestDAO {
    private static ConnectionManager manager;

    static {
        manager = ConnectionManagerPostgresImpl.getInstance();
    }

    @Override
    public List<Test> getTestsByTeacher(Teacher teacher) {
        return null;
    }

    @Override
    public List<Test> getTestsByTeacherDuringPeriod(Teacher teacher, LocalDate begin, LocalDate end) {
        return null;
    }

    @Override
    public List<Work> getWorks() {
//        manager.getConnection().createStatement()
        return null;
    }

    @Override
    public List<Question> getQuestions() {
        return null;
    }

    @Override
    public boolean addQuestions(Collection<Question> questions) {
        return false;
    }

    @Override
    public boolean assignToClass(SchoolClass schoolClass) {
        return false;
    }

    @Override
    public boolean assignDate(LocalDate date) {
        return false;
    }

    @Override
    public boolean addWork(Work test) {
        return false;
    }

    @Override
    public boolean markAbsent(Student student) {
        return false;
    }

    @Override
    public boolean isFullyLoaded() {
        return false;
    }

    @Override
    public boolean saveAsTemplate(Test event) {
        return false;
    }
}
