package inno.service;

import inno.dao.TestDAO;
import inno.exceptions.OrganizerDAOexception;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusTestService {

    private Logger logger;

    private final TestDAO testDAO;

    @Autowired
    public StatusTestService(TestDAO testDAO) {
        this.testDAO = testDAO;
        logger = Logger.getLogger(StatusTestService.class);
    }

    public boolean stopTest(int testId) {
        if (testId <= 0) {
            return false;
        }

        try {
            return testDAO.updateTestStatusById(testId, "uploaded");
        } catch (OrganizerDAOexception organizerDAOexception) {
            logger.error(organizerDAOexception);
        }
        return false;
    }

    public boolean startTest(int testId) {
        if (testId <= 0) {
            return false;
        }

        try {
            return testDAO.updateTestStatusById(testId, "inprogress");
        } catch (OrganizerDAOexception organizerDAOexception) {
            logger.error(organizerDAOexception);
        }
        return false;
    }

    public boolean isInProgress(Integer testId) {
        if (testId <= 0) {
            return false;
        }

        try {
            return testDAO.getTestByStatusAndId(testId, "inprogress");
        } catch (OrganizerDAOexception organizerDAOexception) {
            logger.error(organizerDAOexception);
        }
        return false;

    }
}