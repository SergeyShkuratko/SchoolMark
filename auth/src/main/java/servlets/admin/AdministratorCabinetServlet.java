package servlets.admin;

import classes.School;
import dao.SchoolDAOImpl;
import interfaces.dao.SchoolDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdministratorCabinetServlet extends HttpServlet {

    SchoolDAO schoolDAO = new SchoolDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO обсудить. мне кажется, что в больших масштабах это не будет работать
        // Здесь можно заморочиться с отложенной инициализацией, но это оставим на следующий этап
        List<School> schools = schoolDAO.getAll();
        req.setAttribute("schools", schools);
        req.getRequestDispatcher("/cabinet.jsp").forward(req, resp);
    }
}
