package servlets;

import classes.User;
import exceptions.UserDAOException;
import exceptions.UserNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import services.AuthorizationService;

import javax.servlet.http.HttpSession;

import static classes.CommonSettings.*;
import static exceptions.ErrorDescriptions.*;
import static utils.Settings.*;
@Controller
public class AuthorizationServlet {
    public AuthorizationService authService;

    @Autowired
    public void setAuthService(AuthorizationService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String doGet() {
        return AUTH_JSP;
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ModelAndView doPost(@RequestParam(name = "login") String login,
                               @RequestParam(name = "password") String password,
                               HttpSession session){

        ModelAndView mv = new ModelAndView();
        User user = null;

        try {
            user = authService.auth(login, password);
        } catch (UserNotFoundException e) {
            mv.addObject(ERROR_ATTR, USER_NOT_FOUND);
            mv.setViewName(ERROR_JSP);
            return mv;
        } catch (UserDAOException e) {
            mv.addObject(ERROR_ATTR, DB_ERROR);
            mv.setViewName(ERROR_JSP);
            return mv;
        }
        if (user != null) {
            session.setAttribute(AUTH_USER_ATTRIBUTE, user.getUserId());
            session.setAttribute(AUTH_ROLE_ATTRIBUTE, user.getRole());
            mv.setViewName("redirect:"+ authService.getCabinetUrl(user));
        }else{
            mv.addObject(ERROR_ATTR, DB_ERROR);
            mv.setViewName(ERROR_JSP);
        }
        return mv;
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String doOut(HttpSession session) {
        session.removeAttribute(AUTH_USER_ATTRIBUTE);
        session.removeAttribute(AUTH_ROLE_ATTRIBUTE);
        return "redirect:"+AUTH_PAGE;
    }
}
