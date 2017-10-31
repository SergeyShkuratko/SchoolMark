package studentmodule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.log4j.Logger;
import studentmodule.dto.DTOVariant;
import studentmodule.dto.DTOWork;
import studentmodule.service.WorkService;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;

import static studentmodule.constants.ControllersConstants.RESPONSE_ERROR_MESSAGE;

@Controller
public class WorkLoadServlet {

    private static final Logger logger = Logger.getLogger(WorkLoadServlet.class);
    private final WorkService workService;
    private final ServletContext servletContext;

    @Autowired
    public WorkLoadServlet(WorkService workService, ServletContext servletContext) {
        this.workService = workService;
        this.servletContext = servletContext;
    }

    @RequestMapping(value = "/workload", method = RequestMethod.GET)
    public String showWorkload(
            @RequestParam(name = "id") String inpId, ModelMap model
    ) {
        try {
            int id = Integer.valueOf(inpId);
            DTOWork work = workService.getWorkById(id);
            int variant_id = work.getVariantId();
            List<DTOVariant>  variants = workService.getVariants(work.getTemplId());
            model.addAttribute("variants", variants);
            model.addAttribute("questions",
                    workService.getQuestionListByVariantId(variant_id));
            model.addAttribute("work", workService.getWorkById(id));
            model.addAttribute("files", workService.getStudentFilesByWorkId(id));
            model.addAttribute("teacher_files",
                    workService.getVerificationFilesByVerificationId(id));
        } catch (Exception e) {
            logger.error(RESPONSE_ERROR_MESSAGE + e.getMessage());
            model.addAttribute("whatHappened", RESPONSE_ERROR_MESSAGE + e.getMessage());
            return "WEB-INF/pages/errpages/500-error";
        }
        return "WEB-INF/pages/student/work_load";
    }

    @RequestMapping(value = "/save_variant", method = RequestMethod.POST)
    private String setVariant(HttpServletRequest request) {
        String referer = request.getHeader("referer");
        int variantId = Integer.decode(request.getParameter("variant"));
        int workId = Integer.decode(request.getParameter("work"));
        workService.setWorkVariant(workId, variantId);
        return "redirect:" + referer;
    }

}
