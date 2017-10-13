package com.inno.servlet;

import classes.Test;
import com.inno.dto.TestDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DirectorTestListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TestDto> tests = new ArrayList<>(2);
        tests.add(new TestDto(LocalDate.of(2017, 5, 23), "Иванов Иван Иванович",
                "Математика", "5a", 4.5f));
        tests.add(new TestDto(LocalDate.of(2017, 7, 14), "Петр Петрович Петров",
                "Биология", "7б", 3.7f));

        req.setAttribute("tests", tests);
        req.getRequestDispatcher("/director-test-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
