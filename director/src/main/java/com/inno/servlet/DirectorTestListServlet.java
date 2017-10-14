package com.inno.servlet;

import com.inno.db.dao.FakeTestDao;
import com.inno.db.dto.TestStatisticDto;
import com.inno.service.TestStatisticService;
import com.inno.service.TestStatisticServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectorTestListServlet extends HttpServlet {
    private TestStatisticService testStatisticService = new TestStatisticServiceImpl(new FakeTestDao());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String groupByOrganization = req.getParameter("groupByOrganization");

        if (!"on".equals(groupByOrganization)) {
            req.setAttribute("tests", testStatisticService.getTestsStatistic());
        } else {
            req.setAttribute("teacherTestsMap", testStatisticService.getTestsStatisticGroupedByOwner());
        }

        req.setAttribute("groupByOrganization", groupByOrganization);
        req.getRequestDispatcher("/director-test-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
