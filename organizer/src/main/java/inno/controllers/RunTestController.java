package inno.controllers;

import inno.classes.Commands;
import inno.dao.OrganizerDAO;
import inno.dao.WorkDAO;
import inno.dto.TestDTO;
import inno.exceptions.OrganizerDAOexception;
import inno.service.RunTestService;
import inno.servlets.RunTestServlet;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by nkm on 26.10.2017.
 */
@Controller
@RequestMapping("/test-run")
public class RunTestController {

    private static Logger logger = Logger.getLogger(RunTestServlet.class);

    @RequestMapping(method = RequestMethod.GET, params = {"test_id"})
    protected ModelAndView processTest(@RequestParam("test_id") Integer test_id) {

        //<---- TODO может быть здесь стоит проверять есть ли такой тест в БД вообще

        ModelAndView modelAndView = new ModelAndView("test_run");

        try {
            //Просим сервис стартануть контрольную работу
            RunTestService.createWorksForTestIfNotExist(test_id);
            //получаем тест из БД
            TestDTO test = OrganizerDAO.getTestById(test_id);
            //добавляем тест
            modelAndView.addObject("test", test);
            //добавляем работы
            modelAndView.addObject("works", OrganizerDAO.getAllWorksByTestId(test.getId()));

        } catch (OrganizerDAOexception e) {
            logger.error(e);
        }

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, params = {"work_id", "presence"})
    protected void processTestUpdatePresence(@RequestParam("work_id") Integer workId,
                                             @RequestParam(value = "presence", required = false) Boolean presence) {

        if (presence != null) {
            try {
                WorkDAO.updatePresenceStatusByID(workId, presence);
            } catch (OrganizerDAOexception organizerDAOexception) {
                logger.error(organizerDAOexception);
            }
        }
    }


    /**
     * Проверяет статус загруженых работ онлайн через JSON
     */
    @RequestMapping(method = RequestMethod.GET,params = "get_upload_info_for_test", produces = "application/json")
    protected String processTest3(@RequestParam("get_upload_info_for_test") Integer testId) {

//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");

        return RunTestService.getWorksStatusByTestId(testId);
    }


    /**
     * Устанавливает параметры от учителя, подтвержает ли качество загруженных работ или нет.
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    protected String processTest4(@RequestParam("work_id") Integer workId,
                                  @RequestParam("action") String command) {

//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//        PrintWriter writer = resp.getWriter();

        String resultJsonString = RunTestService.setTeachersChoiceForWork(workId,
                Commands.valueOf(command.toUpperCase()));

        return resultJsonString;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    protected String processTest5(@RequestParam("pages_images") Integer pagesImages) {

//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");

        return RunTestService.getWorkPagesAsJson(pagesImages);
    }

}
