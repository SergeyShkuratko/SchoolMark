package com.inno.servlet;

import com.inno.db.dao.FakeTestDao;
import com.inno.service.TestStatisticService;
import com.inno.service.TestStatisticServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DirectorTestViewServlet extends HttpServlet {
    private TestStatisticService testStatisticService;

    public DirectorTestViewServlet() {
        testStatisticService = new TestStatisticServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int testId;
        try {
            testId = Integer.parseInt(req.getParameter("testId"));
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/director-test-list");
            return;
        }

        req.setAttribute("testAndWorkInfo", testStatisticService.getTestAndWorksInfo(testId));
        req.getRequestDispatcher("/director-test-view.jsp").forward(req, resp);
    }
}
