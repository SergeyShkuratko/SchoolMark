package inno.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import inno.dao.OrganizerDAO;
import inno.dao.WorkDAO;
import inno.dto.TestDTO;
import inno.exceptions.OrganizerDAOexception;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test-run")
public class RunTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("cp1251");
        int test_id = req.getParameter("test_id") != null ?
                Integer.parseInt(req.getParameter("test_id")) : 0;
        if (test_id > 0) {
            try {
                if (!OrganizerDAO.isWorksExists(test_id))
                    OrganizerDAO.createWorksForTest(test_id); //создаем работы, если еще нет

                //получаем тест из БД
                TestDTO test = OrganizerDAO.getTestById(test_id);

                //добавляем работы
                req.setAttribute("works", OrganizerDAO.getAllWorksByTestId(test.getId()));

                //добавляем тест
                req.setAttribute("test", test);
            } catch (OrganizerDAOexception e) {
                throw new ServletException(e);
            }
            req.getRequestDispatcher("test_run.jsp").forward(req, resp);
        }


        if (req.getParameter("work_id") != null) {
            if (req.getParameter("presence") != null) {
                try {
                    WorkDAO.updatePresenceStatusByID(
                            Integer.parseInt((req.getParameter("work_id"))),
                            Boolean.parseBoolean(req.getParameter("presence")));
                } catch (OrganizerDAOexception organizerDAOexception) {
                    organizerDAOexception.printStackTrace();
                }
            }
        }

        if (req.getParameter("get_upload_info_for_test") != null) {
            try {
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter writer = resp.getWriter();


                Gson gson = new Gson();

                String json = gson.toJson(
                        WorkDAO.getWorksStatusByTest(
                                Integer.parseInt(
                                        req.getParameter("get_upload_info_for_test")))
                );
                writer.print(json);


            } catch (OrganizerDAOexception organizerDAOexception) {
                organizerDAOexception.printStackTrace();
            }
        }


        if (req.getParameter("action") != null &&
                req.getParameter("id") != null) {
            try {
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter writer = resp.getWriter();


                Gson gson = new Gson();
                if (req.getParameter("action").equals("success")) {
                    String json = gson.toJson(
                            WorkDAO.updateStatusById(
                                    Integer.parseInt(
                                            req.getParameter("id")), "confirmed")
                    );
                    writer.print(json);
                }
                if (req.getParameter("action").equals("decline")) {
                    String json = gson.toJson(
                            WorkDAO.updateStatusById(
                                    Integer.parseInt(
                                            req.getParameter("id")), "declined")
                    );
                    writer.print(json);
                }


            } catch (OrganizerDAOexception organizerDAOexception) {
                organizerDAOexception.printStackTrace();
            }
        }


        if (req.getParameter("pages_images") != null) {

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter writer = resp.getWriter();


            Gson gson = new Gson();

            String pages_json = null;
            try {
                pages_json = gson.toJson(
                        WorkDAO.getPagesImgByWork(
                                Integer.parseInt(

                                        req.getParameter("pages_images")))
                );
            } catch (OrganizerDAOexception organizerDAOexception) {
                organizerDAOexception.printStackTrace();
            }
            writer.print(pages_json);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
