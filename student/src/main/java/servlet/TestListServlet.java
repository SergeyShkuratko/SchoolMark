package servlet;

import classes.*;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SchoolType schoolType = new SchoolType(1, "крутяцкая");
        School school = new School(1, "Школа №20", "Татарстан", "Казань", schoolType);
        SchoolClass schoolClass = new SchoolClass(1, 5, "5");
        Student student = new Student(1, 1, "login", LocalDate.now(),"Иван", "Иванов", "Иванович", schoolClass, school);

        List<Work> works = WorkService.getAllWork(student);

        req.setAttribute("works", works);
        req.getRequestDispatcher("/testlist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Метод пост списка контрольных");
    }
}
