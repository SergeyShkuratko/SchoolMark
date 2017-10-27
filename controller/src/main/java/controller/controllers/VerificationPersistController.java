package controller.controllers;

import classes.CommonSettings;
import controller.dao.dto.VerificationResultDTO;
import controller.service.VerificationResultImpl;
import controller.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/persistVerificationResult")
@Controller
public class VerificationPersistController {

    VerificationService verificationService;


    @Autowired
    public void setVerificationService(VerificationService verificationService) {
        this.verificationService = verificationService;
    }


    protected @ResponseBody String verify(HttpServletRequest req) {

        int mark = Integer.parseInt(req.getParameter("mark"));
        String comment = req.getParameter("comment");
        int workId = Integer.parseInt(req.getParameter("workId"));
        int userId = (Integer) req.getSession().getAttribute(CommonSettings.AUTH_USER_ATTRIBUTE);

        VerificationResultDTO resultDTO = new VerificationResultDTO(workId, userId, mark, comment);

        if (verificationService.persistVerificationResult(resultDTO)) {
            return "{\"result\":\"ok\"}";
        } else {
            return "{\"result\":\"error\"}";
        }
    }
}
