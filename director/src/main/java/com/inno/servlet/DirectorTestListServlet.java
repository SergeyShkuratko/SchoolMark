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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectorTestListServlet extends HttpServlet {
    private TestStatisticService testStatisticService;

    public DirectorTestListServlet() {
        testStatisticService = new TestStatisticServiceImpl(new FakeTestDao());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String groupByOrganization = req.getParameter("groupByOrganization");
        LocalDate dateFrom = parseDate(req.getParameter("dateFrom"));
        LocalDate dateTo = parseDate(req.getParameter("dateTo"));

        if (!"on".equals(groupByOrganization)) {
            req.setAttribute("tests", testStatisticService.getTestsStatistic(dateFrom, dateTo));
        } else {
            req.setAttribute("teacherTestsMap",
                    testStatisticService.getTestsStatisticGroupedByOwner(dateFrom, dateTo));
        }

        req.getRequestDispatcher("/director-test-list.jsp").forward(req, resp);
    }

    private LocalDate parseDate(String dateStr) {
        if (dateStr == null) {
            return null;
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        try {
            return LocalDate.parse(dateStr, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
