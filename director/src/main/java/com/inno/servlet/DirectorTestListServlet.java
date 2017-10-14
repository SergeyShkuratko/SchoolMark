package com.inno.servlet;

import com.inno.db.dao.FakeTestDao;
import com.inno.db.dto.TestDto;
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
            req.setAttribute("tests", testStatisticService.findAll());
        } else {
            Map<String, List<TestDto>> teacherTestsMap = new HashMap<>();

            List<TestDto> ivanovTests = new ArrayList<>(2);
            ivanovTests.add(new TestDto(LocalDate.of(2017, 5, 23), "Иванов Иван Иванович",
                    "Математика", "5a", 4.5f));
            ivanovTests.add(new TestDto(LocalDate.of(2017, 7, 14), "Иванов Иван Иванович",
                    "Биология", "7б", 3.7f));
            teacherTestsMap.put("Иванов Иван Иванович", ivanovTests);

            List<TestDto> petronTests = new ArrayList<>(2);
            petronTests.add(new TestDto(LocalDate.of(2017, 2, 2), "Петр Петрович Петров",
                    "Математика", "3a", 4.3f));
            teacherTestsMap.put("Петр Петрович Петров", petronTests);

            req.setAttribute("teacherTestsMap", teacherTestsMap);
        }

        req.setAttribute("groupByOrganization", groupByOrganization);
        req.getRequestDispatcher("/director-test-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
