package template.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import template.dao.TestTemplateDAOImplementation;
import template.dto.TestTemplate;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TemplateListController {
    @RequestMapping(value = "/template-list", method = RequestMethod.GET)
    public ModelAndView getTemplateListGet() {
        List<TestTemplate> templates = TestTemplateDAOImplementation.getAllTemplatesByTeacher();
        return new ModelAndView("template-list").addObject("templates", templates);
    }

    @RequestMapping(value = "/template-list", method = RequestMethod.POST)
    public String getTemplateListPost(HttpSession session, @RequestParam(name = "templateId") int templateId) {
        TestTemplate testTemplate = TestTemplateDAOImplementation.getTemplateByIdCascade(templateId);
        session.setAttribute("testTemplate", testTemplate);
        return "redirect:test";
    }

}
