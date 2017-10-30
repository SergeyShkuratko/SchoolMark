package inno.service;

import inno.dao.TestDAO;
import inno.exceptions.OrganizerDAOexception;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StopTestService {

    private Logger logger;

    private final TestDAO testDAO;

    @Autowired
    public StopTestService(TestDAO testDAO) {
        this.testDAO = testDAO;
        logger = Logger.getLogger(StopTestService.class);
    }

    public boolean stopTest(int testId) {
        if (testId <= 0) {
            return false;
        }

        try {
            return testDAO.doneTest(testId);
        } catch (OrganizerDAOexception organizerDAOexception) {
            logger.error(organizerDAOexception);
        }
        return false;
    }
}