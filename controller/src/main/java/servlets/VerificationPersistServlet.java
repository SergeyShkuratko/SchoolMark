package servlets;

import DAO.DTO.VerificationResultDTO;
import service.VerificationResultImpl;
import service.VerificationService;

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
        int verifierId = 30; // ITS MOCK

        VerificationResultDTO resultDTO = new VerificationResultDTO(workId, verifierId, mark, comment);

        if (verificationService.persistVerificationResult(resultDTO)) {
            resp.getWriter().print("{\"result\":\"ok\"}");
        } else {
            resp.getWriter().print("{\"result\":\"error\"}");
        }

    }
}
