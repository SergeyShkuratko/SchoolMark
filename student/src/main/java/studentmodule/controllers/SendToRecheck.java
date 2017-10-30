package studentmodule.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import studentmodule.exception.DAOStudentWorkException;
import studentmodule.service.WorkService;

import static studentmodule.constants.ControllersConstants.RESPONSE_ERROR_MESSAGE;

@Controller
public class SendToRecheck {

    private final WorkService workService;
    private static final Logger logger = Logger.getLogger(SendToRecheck.class);

    public SendToRecheck(WorkService workService) {
        this.workService = workService;
    }

    @RequestMapping(value = "/workload/sendtorecheck", method = RequestMethod.POST)
    public String sendToRecheck(
            @RequestParam(name = "workId") String workId,
            ModelMap model
    ) {
        try {
            workService.setWorkStatus(Integer.valueOf(workId), "reverification");
            return "redirect:/testlist";
        } catch(NumberFormatException nfe) {
            logger.error(RESPONSE_ERROR_MESSAGE + nfe.getMessage());
            model.addAttribute("whatHappened", RESPONSE_ERROR_MESSAGE + nfe.getMessage());
            return "errpages/500-error";
        } catch (DAOStudentWorkException e) {
            logger.error(RESPONSE_ERROR_MESSAGE + e.getMessage());
            model.addAttribute("whatHappened", RESPONSE_ERROR_MESSAGE + e.getMessage());
            return "errpages/500-error";
        }
    }

}
