package template.servlets;


import template.dao.TestTemplateDAOImplementation;
import template.dto.TestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TemplateListServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            List<TestTemplate> templates = TestTemplateDAOImplementation.getAllTemplatesByTeacher();
            req.setAttribute("templates", templates);
            getServletContext().getRequestDispatcher("/template-list.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TestTemplate testTemplate = TestTemplateDAOImplementation.
                getTemplateByIdCascade(new Integer(req.getParameter("templateId")));

        req.getSession().setAttribute("testTemplate", testTemplate);
        resp.sendRedirect(getServletContext().getContextPath() + "/test");

//        if(req.getParameter("templateId") == null) {
//            List<TestTemplate> templates = TestTemplateDAOImplementation.getAllTemplatesByTeacher();
//            req.setAttribute("templates", templates);
//            getServletContext().getRequestDispatcher("/template-list.jsp").forward(req, resp);
//        }
//        else {
//            TestTemplate testTemplate = TestTemplateDAOImplementation.
//                    getTemplateByIdCascade(new Integer(req.getParameter("templateId")));
//
//            req.getSession().setAttribute("testTemplate", testTemplate);
//            getServletContext().getRequestDispatcher("/test-template").forward(req, resp);
//        }
    }
}
