package inno.service;

import com.google.gson.Gson;
import inno.dao.OrganizerDAO;
import inno.dao.WorkDAO;
import inno.exceptions.OrganizerDAOexception;
import inno.servlets.RunTestServlet;
import org.apache.log4j.Logger;

public class RunTestService {

    private static Logger logger = Logger.getLogger(RunTestServlet.class);

    public static boolean createWorksForTestIfNotExist(int test_id) {
        boolean result = false;
        //создаем работы, если еще нет
        try {
            if (!OrganizerDAO.isWorksExists(test_id)) {
                result = OrganizerDAO.createWorksForTest(test_id);
            }
        } catch (OrganizerDAOexception organizerDAOexception) {
            logger.error(organizerDAOexception);
            organizerDAOexception.printStackTrace();
        }
        return result;
    }


    public static String getWorksStatusByTestId(int testId) {
        Gson gson = new Gson();
        String json = null;
        try {
            json = gson.toJson(
                    WorkDAO.getWorksStatusByTest(testId));
        } catch (OrganizerDAOexception organizerDAOexception) {
            logger.error(organizerDAOexception);
            organizerDAOexception.printStackTrace();
        }
        return json;
    }

    public static String setTeachersChoiceForWork(int id, String action) {
        Gson gson = new Gson();
        if (action.equals("success")) {
            try {
                return gson.toJson(WorkDAO.updateStatusById(id, "confirmed"));
            } catch (OrganizerDAOexception organizerDAOexception) {
                logger.error(organizerDAOexception);
                organizerDAOexception.printStackTrace();
            }
        }
        if (action.equals("decline")) {
            try {
                return gson.toJson(WorkDAO.updateStatusById(id, "declined"));
            } catch (OrganizerDAOexception organizerDAOexception) {
                logger.error(organizerDAOexception);
                organizerDAOexception.printStackTrace();
            }
        }
        return gson.toJson(false);
    }

    public static String getWorkPagesAsJson(int workId) {

        Gson gson = new Gson();
        String pagesJson = null;

        try {
            pagesJson = gson.toJson(
                    WorkDAO.getPagesImgByWork(workId));

        } catch (OrganizerDAOexception organizerDAOexception) {
            logger.error(organizerDAOexception);
            organizerDAOexception.printStackTrace();
        }
        return pagesJson;
    }


}
