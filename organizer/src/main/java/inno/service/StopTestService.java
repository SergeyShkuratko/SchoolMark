package inno.service;

import inno.dao.TestDAO;
import inno.exceptions.OrganizerDAOexception;
import org.apache.log4j.Logger;

public class StopTestService {

    private static Logger logger = Logger.getLogger(StopTestService.class);

    public static boolean stopTest(int testId) {
        if (testId <= 0) {
            return false;
        }

        try {
            return TestDAO.doneTest(testId);
        } catch (OrganizerDAOexception organizerDAOexception) {
            logger.error(organizerDAOexception);
        }
        return false;
    }
}

