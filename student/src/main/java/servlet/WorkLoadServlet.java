package servlet;

import service.WorkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/workload")
public class WorkLoadServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.decode(req.getParameter("id"));
        req.setAttribute("work", WorkService.getWorkById(id-1));
        req.setAttribute("questions", WorkService.getQuestionListByWorkId(id));
        req.setAttribute("files", WorkService.getFilesByWorkId(id));
        req.setAttribute("teacher_files", WorkService.getFilesByWorkId(id));
        req.getRequestDispatcher("/work_load.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
