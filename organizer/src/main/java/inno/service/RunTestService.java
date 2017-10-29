package inno.service;

import com.google.gson.Gson;
import inno.classes.Commands;
import inno.dao.OrganizerDAO;
import inno.dao.WorkDAO;
import inno.exceptions.OrganizerDAOexception;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RunTestService {

    private Logger logger;

    private final OrganizerDAO organizerDAO;
    private final WorkDAO workDAO;

    @Autowired
    public RunTestService(OrganizerDAO organizerDAO, WorkDAO workDAO) {
        this.organizerDAO = organizerDAO;
        this.workDAO = workDAO;
        logger = Logger.getLogger(RunTestService.class);
    }


    public boolean createWorksForTestIfNotExist(int testId) {
        boolean result = false;
        //создаем работы, если еще нет
        try {
            if (!organizerDAO.isWorksExists(testId)) {
                result = organizerDAO.createWorksForTest(testId);
            }
        } catch (OrganizerDAOexception organizerDAOexception) {
            logger.error(organizerDAOexception);
        }
        return result;
    }


    public String getWorksStatusByTestId(int testId) {
        Gson gson = new Gson();
        String json = null;
        try {
            json = gson.toJson(
                    workDAO.getWorksStatusByTest(testId));
        } catch (OrganizerDAOexception organizerDAOexception) {
            logger.error(organizerDAOexception);
        }
        return json;
    }

    public String setTeachersChoiceForWork(int id, Commands action) {
        Gson gson = new Gson();
        String json = null;
        switch (action) {
            case SUCCESS:
                try {
                    json =  gson.toJson(workDAO.updateStatusById(id, "confirmed"));
                } catch (OrganizerDAOexception organizerDAOexception) {
                    logger.error(organizerDAOexception);
                }
                break;
            case DECLINE:
                try {
                    json =  gson.toJson(workDAO.updateStatusById(id, "declined"));
                } catch (OrganizerDAOexception organizerDAOexception) {
                }
                break;
            default:
                json =  gson.toJson(false);
                break;
        }
        return json;
    }

    public String getWorkPagesAsJson(int workId) {

        Gson gson = new Gson();
        String pagesJson = "";

        try {
            pagesJson = gson.toJson(
                    workDAO.getPagesImgByWork(workId));

        } catch (OrganizerDAOexception organizerDAOexception) {
            logger.error(organizerDAOexception);
        }
        return pagesJson;
    }


}
