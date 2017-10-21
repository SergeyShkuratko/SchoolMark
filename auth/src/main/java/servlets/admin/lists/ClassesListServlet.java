package servlets.admin.lists;

import classes.SchoolClass;
import dao.SchoolDAOImpl;
import exceptions.SchoolDAOException;
import interfaces.dao.SchoolDAO;
import interfaces.dao.SchoolsDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ClassesListServlet extends HttpServlet {

    SchoolsDAO schoolDAO = new SchoolDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("school");
        if (param == null) {
            // TODO something
        }
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();
        int id = Integer.valueOf(req.getParameter("school"));
        List<SchoolClass> classes = null;
        try {
            classes = schoolDAO.getAllClasses(id);
        } catch (SchoolDAOException e) {
            e.printStackTrace();
        }
        classes.stream().forEach((s) -> pw.println("<li>" + s.getName() + "</li>"));
    }
}
