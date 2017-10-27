package controllers;

import classes.Role;
import classes.User;
import exceptions.RegisterUrlNotFoundException;
import exceptions.RoleDAOException;
import exceptions.UserDAOException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import services.RegistrationService;

import javax.servlet.http.HttpSession;

import static classes.CommonSettings.AUTH_ROLE_ATTRIBUTE;
import static classes.CommonSettings.AUTH_USER_ATTRIBUTE;
import static exceptions.ErrorDescriptions.*;
import static utils.Settings.*;
@Controller
public class RegistrationController {
    public static final String REG_SESSION_ATTRIBUTE_ROLE = "role";
    private static Logger logger = Logger.getLogger(RegistrationController.class);
    private RegistrationService registrationService;

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @RequestMapping(value = "/register/{regUrl}", method = RequestMethod.GET)
    protected ModelAndView doGet(@PathVariable("regUrl") String regUrl, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Role role = (Role)session.getAttribute(AUTH_ROLE_ATTRIBUTE);
        if (role == null) {
            try {
                role = registrationService.getRoleFromUrl(regUrl);
                session.setAttribute(REG_SESSION_ATTRIBUTE_ROLE, role);
                mv.setViewName("redirect:/"+REGISTRATION_JSP+".jsp");
            } catch (RoleDAOException e) {
                logger.error(e);
                mv.addObject(ERROR_ATTR, DB_ERROR);
                mv.setViewName(ERROR_JSP);
            } catch (RegisterUrlNotFoundException e) {
                logger.error(e);
                mv.addObject(ERROR_ATTR, WRONG_REG_URL);
                mv.setViewName(ERROR_JSP);
            }
        } else {
            mv.setViewName("redirect:"+AUTH_PAGE);
        }
        return mv;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    protected ModelAndView doPost(@RequestParam("login") String login, @RequestParam("password") String password,
                                HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Role role = (Role)session.getAttribute(REG_SESSION_ATTRIBUTE_ROLE);
        if(role != null) {
            try {
                User user = registrationService.register(login, password, role);
                session.setAttribute(AUTH_USER_ATTRIBUTE, user.getUserId());
                session.setAttribute(AUTH_ROLE_ATTRIBUTE, user.getRole());
                session.removeAttribute(REG_SESSION_ATTRIBUTE_ROLE);
                mv.setViewName("redirect:" + AUTH_PAGE);
            } catch (UserDAOException e) {
                mv.addObject(ERROR_ATTR, REGISTER_ERROR);
                mv.setViewName(ERROR_JSP);
            }
        }else{
            mv.addObject(ERROR_ATTR, REGISTER_ERROR);
            mv.setViewName(ERROR_JSP);
        }
        return mv;
    }
}
