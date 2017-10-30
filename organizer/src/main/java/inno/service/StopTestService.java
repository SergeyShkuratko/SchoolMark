package inno.service;

import classes.dto.TeacherDTO;
import exceptions.SchoolTeacherDAOException;
import inno.dao.OrganizerDAO;

import inno.dao.SchoolTeacherDAO;
import inno.dao.TestDAO;
import inno.dao.WorkDAO;
import inno.dto.TestDTO;
import inno.exceptions.OrganizerDAOexception;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class StopTestService {

    private Logger logger;

    private final TestDAO testDAO;
    private final SchoolTeacherDAO teacherDAO;
    private final OrganizerDAO organizerDAO;
    private final WorkDAO workDAO;


    @Autowired
    public StopTestService(TestDAO testDAO, SchoolTeacherDAO teacherDAO, OrganizerDAO organizerDAO, WorkDAO workDAO) {
        this.testDAO = testDAO;
        this.teacherDAO = teacherDAO;
        this.organizerDAO = organizerDAO;
        this.workDAO = workDAO;

        logger = Logger.getLogger(StopTestService.class);
    }

    public boolean stopTest(int testId) {
        if (testId <= 0) {
            return false;
        }

        try {
            boolean b = testDAO.doneTest(testId);
            setWorkVerifier(testId);
            return b;
        } catch (OrganizerDAOexception organizerDAOexception) {
            logger.error(organizerDAOexception);
        } catch (SchoolTeacherDAOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void setWorkVerifier(int testId) throws OrganizerDAOexception, SchoolTeacherDAOException {
        TestDTO test = organizerDAO.getTestById(testId);
        List<TeacherDTO> teachers = teacherDAO.getAllTeacherWithoutOrganizer(test.getOwnerId());
        Random rnd = new Random();
        int index = rnd.nextInt(teachers.size());
        int teacherId = teachers.get(index).getId();
        List<Integer> workIdList = workDAO.getAllWorksIdByTestId(testId);
        for(int workId : workIdList) {
            workDAO.updateWorkVerifierByWorkId(workId, teacherId);
        }
    }
}