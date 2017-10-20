package servlet;

import classes.*;
import dao.DAOStudentWork;
import dto.DTOWork;
import org.apache.log4j.Logger;
import service.WorkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@WebServlet("/testlist")
public class TestListServlet extends HttpServlet{
    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int student_id = 1;

        boolean haveNewTest = true;
        req.setAttribute("have_new_test", haveNewTest);
        List<DTOWork> works = null;
        try {
            works = WorkService.getAllWork(student_id);
            req.setAttribute("works", works);
        } catch (DAOStudentWork.DAOStudentWorkException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/testlist.jsp").forward(req, resp);
    }
}
