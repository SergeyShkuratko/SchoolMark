package servlets;

import classes.Role;
import classes.School;
import classes.SchoolClass;
import classes.dto.SchoolDTO;
import services.SchoolService;
import services.impl.SchoolServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserInfoServlet extends HttpServlet {

    private static SchoolService schoolService = new SchoolServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getParameter()
        //TODO load school info
        SchoolDTO school = schoolService.getSchoolById(schoolId);
        SchoolClass schoolClass = null;
        req.setAttribute("school", school);
        req.setAttribute("class", schoolClass);
        switch (role) {
            case "teacher":
                break;
            case "student":
                break;
            case "director":
                break;
        }

    }
}
