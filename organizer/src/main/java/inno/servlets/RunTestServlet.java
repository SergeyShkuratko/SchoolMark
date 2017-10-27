package inno.servlets;

import inno.classes.Commands;
import inno.dao.OrganizerDAO;
import inno.dao.WorkDAO;
import inno.dto.TestDTO;
import inno.exceptions.OrganizerDAOexception;
import inno.service.RunTestService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/test-run")
public class RunTestServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(RunTestServlet.class);

    private OrganizerDAO organizerDAO = new OrganizerDAO();
    private WorkDAO workDAO = new WorkDAO();
    private RunTestService runTestService = new RunTestService(organizerDAO, workDAO);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int test_id = req.getParameter("test_id") != null ?
                Integer.parseInt(req.getParameter("test_id")) : 0;

        if (test_id > 0) {
            try {
                //Просим сервис стартануть контрольную работу
                runTestService.createWorksForTestIfNotExist(test_id);

                //получаем тест из БД
                TestDTO test = organizerDAO.getTestById(test_id);

                //добавляем работы
                req.setAttribute("works", organizerDAO.getAllWorksByTestId(test.getId()));

                //добавляем тест
                req.setAttribute("test", test);
            } catch (OrganizerDAOexception e) {
                logger.error(e);
                throw new ServletException(e);
            }
            req.getRequestDispatcher("/WEB-INF/organizerpages/test_run.jsp").forward(req, resp);
        }


        if (req.getParameter("work_id") != null) {
            if (req.getParameter("presence") != null) {
                try {
                    workDAO.updatePresenceStatusByID(
                            Integer.parseInt((req.getParameter("work_id"))),
                            Boolean.parseBoolean(req.getParameter("presence")));
                } catch (OrganizerDAOexception organizerDAOexception) {
                    logger.error(organizerDAOexception);
                }
            }
        }

        /**
         * Проверяет статус загруженых работ онлайн через JSON
         */
        if (req.getParameter("get_upload_info_for_test") != null) {

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            int testId = Integer.parseInt(req.getParameter("get_upload_info_for_test"));

            resp.getWriter().print(runTestService.getWorksStatusByTestId(testId));
        }


        /**
         * Устанавливает параметры от учителя, подтвержает ли качество загруженных работ или нет.
         */
        if (req.getParameter("action") != null &&
                req.getParameter("id") != null) {

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter writer = resp.getWriter();

            String resultJsonString =
                    runTestService.setTeachersChoiceForWork(
                            Integer.parseInt(req.getParameter("id")),
                            Commands.valueOf(req.getParameter("action").toUpperCase()));

            writer.print(resultJsonString);
        }


        if (req.getParameter("pages_images") != null) {

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().print(
                    runTestService.getWorkPagesAsJson(
                            Integer.parseInt(
                                    req.getParameter("pages_images"))));
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
