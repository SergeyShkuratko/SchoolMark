package servlets.admin.lists;

import classes.Teacher;
import classes.dto.TeacherDTO;
import dao.SchoolTeacherDAOImpl;
import exceptions.SchoolTeacherDAOException;
import interfaces.dao.SchoolTeacherDAO;
import interfaces.dao.TeacherDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TeacherListServlet extends HttpServlet {

    private SchoolTeacherDAO teacherDAO = new SchoolTeacherDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("school");
        if (param == null) {
            // TODO something
        }
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();
        int id = Integer.valueOf(req.getParameter("school"));
        List<TeacherDTO> classes = null;
        try {
            classes = teacherDAO.getTeacherBySchool(id);
            classes.stream().forEach((t) -> pw.println("<li>" + t.firstname + t.lastname + "</li>"));
        } catch (SchoolTeacherDAOException e) {
            e.printStackTrace();
        }

    }
}
