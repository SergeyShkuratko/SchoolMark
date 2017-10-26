package servlets;

import classes.CommonSettings;
import student.dao.dto.VerificationResultDTO;
import student.service.VerificationResultImpl;
import student.service.VerificationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/persistVerificationResult")
public class VerificationPersistServlet extends HttpServlet {

    VerificationService verificationService = new VerificationResultImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int mark = Integer.parseInt(req.getParameter("mark"));
        String comment = req.getParameter("comment");
        int workId = Integer.parseInt(req.getParameter("workId"));
        int userId = (Integer) req.getSession().getAttribute(CommonSettings.AUTH_USER_ATTRIBUTE);

        VerificationResultDTO resultDTO = new VerificationResultDTO(workId, userId, mark, comment);

        if (verificationService.persistVerificationResult(resultDTO)) {
            resp.getWriter().print("{\"result\":\"ok\"}");
        } else {
            resp.getWriter().print("{\"result\":\"error\"}");
        }

    }
}
