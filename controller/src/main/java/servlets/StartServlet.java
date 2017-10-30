package servlets;

import classes.CommonSettings;
import studentmodule.dao.dto.TestsDTO;
import studentmodule.service.TestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/start")
public class StartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (Integer) req.getSession().getAttribute(CommonSettings.AUTH_USER_ATTRIBUTE);
        List<TestsDTO> testService = new TestServiceImpl().getWorksForVerifier(userId);
        req.setAttribute("tests", testService);
        req.getRequestDispatcher("/controller.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
