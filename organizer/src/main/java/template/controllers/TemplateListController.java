package template.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import template.dto.TestTemplate;
import template.services.TestTemplateService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TemplateListController {

    final private TestTemplateService testTemplateService;

    @Autowired
    public TemplateListController(TestTemplateService testTemplateService) {
        this.testTemplateService = testTemplateService;
    }

    @RequestMapping(value = "/template-list", method = RequestMethod.GET)
    public ModelAndView getTemplateListGet() {
        List<TestTemplate> templates = testTemplateService.getAllTemplatesByTeacher();
        return new ModelAndView("/template-list.jsp").addObject("templates", templates);
    }

    @RequestMapping(value = "/template-list", method = RequestMethod.POST)
    public String getTemplateListPost(HttpSession session, @RequestParam(name = "templateId") int templateId) {
        TestTemplate testTemplate = testTemplateService.getTemplateByIdCascade(templateId);
        session.setAttribute("testTemplate", testTemplate);
        return "redirect:test";
    }
}