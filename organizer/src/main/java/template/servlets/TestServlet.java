package template.servlets;

import template.dto.Test;
import template.services.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nkm on 13.10.2017.
 */
public class TestServlet extends HttpServlet {
    public static TestService testService = new TestService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/test.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        Test test = testService.getTestFromReq(req);
//        Test test = null;

        req.getSession().setAttribute("test", test);
        getServletContext().getRequestDispatcher("/test-template").forward(req, resp);
        //resp.sendRedirect(getServletContext().getContextPath() + "/test-template");
    }
}