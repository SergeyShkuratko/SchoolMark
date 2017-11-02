package controller.controllers;

import controller.dao.dto.VerificationResultDTO;
import controller.service.TestService;
import controller.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import security.CustomUser;

//@WebServlet("/persistVerificationResult")
@Controller
public class VerificationPersistController {

    VerificationService verificationService;

    TestService testService;

    @Autowired
    public VerificationPersistController(TestService testService) {
        this.testService = testService;
    }


    @Autowired
    public void setVerificationService(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @RequestMapping("/persistVerificationResult")
    protected @ResponseBody
    String verify(@RequestParam("mark") int mark,
                  @RequestParam("comment") String comment,
                  @RequestParam("workId") int workId) {

        int userId = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        VerificationResultDTO resultDTO = new VerificationResultDTO(workId, userId, mark, comment);

        if (verificationService.persistVerificationResult(resultDTO) && testService.setWorkStatusVerified(workId)) {
            return "{\"result\":\"ok\"}";
        } else {
            return "{\"result\":\"error\"}";
        }
    }
}
