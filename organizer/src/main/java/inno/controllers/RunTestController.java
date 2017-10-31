package inno.controllers;

import inno.classes.Commands;
import inno.dao.OrganizerDAO;
import inno.dao.WorkDAO;
import inno.dto.TestDTO;
import inno.exceptions.OrganizerDAOexception;
import inno.service.RunTestService;
import inno.service.StatusTestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by nkm on 26.10.2017.
 */
@Controller
@RequestMapping("/test-run")
public class RunTestController {

    private Logger logger;

    private final OrganizerDAO organizerDAO;
    private final WorkDAO workDAO;
    private final RunTestService runTestService;
    private final StatusTestService StatusTestService;

    @Autowired
    public RunTestController(OrganizerDAO organizerDAO, WorkDAO workDAO, RunTestService runTestService, StatusTestService StatusTestService) {
        this.organizerDAO = organizerDAO;
        this.workDAO = workDAO;
        this.runTestService = runTestService;
        this.StatusTestService = StatusTestService;
        logger = Logger.getLogger(RunTestController.class);
    }


    @RequestMapping(method = RequestMethod.GET, params = {"test_id"})
    protected ModelAndView processTest(@RequestParam("test_id") Integer testId) {

        ModelAndView modelAndView = null;
        try {
            //отмечаем контрольную как в процессе
            //if (!StatusTestService.isInProgress(testId)) {
                StatusTestService.startTest(testId);
                //создаем работы для учеников
                runTestService.createWorksForTestIfNotExist(testId);
                //получаем тест из БД
                TestDTO test = organizerDAO.getTestById(testId);

                modelAndView = new ModelAndView("test_run");


                //добавляем тест
                modelAndView.addObject("test", test);
                //добавляем работы
                modelAndView.addObject("works", organizerDAO.getAllWorksByTestId(test.getId()));
            /*} else {
                modelAndView = new ModelAndView("test_in_progress");
                TestDTO test = organizerDAO.getTestById(testId);
                modelAndView.addObject("test", test);
            }*/
        } catch (OrganizerDAOexception e) {
            logger.error(e);
        }

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, params = {"work_id", "presence"})
    @ResponseStatus(HttpStatus.OK)
    protected void processTestUpdatePresence(@RequestParam("work_id") Integer workId,
                                             @RequestParam(value = "presence", required = false) Boolean presence) {
        if (presence != null) {
            try {
                workDAO.updatePresenceStatusByID(workId, presence);
            } catch (OrganizerDAOexception organizerDAOexception) {
                logger.error(organizerDAOexception);
            }
        }
    }

    /**
     * Проверяет статус загруженых работ онлайн через JSON
     */
    @RequestMapping(method = RequestMethod.GET, params = "get_upload_info_for_test", produces = "application/json")
    protected @ResponseBody
    String processTestWorkStatus(@RequestParam("get_upload_info_for_test") Integer testId) {
        return runTestService.getWorksStatusByTestId(testId);
    }


    /**
     * Устанавливает параметры от учителя, подтвержает ли качество загруженных работ или нет.
     */
    @RequestMapping(method = RequestMethod.GET, params = {"id", "action"}, produces = "application/json")
    protected @ResponseBody
    String processTestTeachersChoice(
            @RequestParam("id") Integer workId, @RequestParam("action") String command) {
        return runTestService.setTeachersChoiceForWork(workId,
                Commands.valueOf(command.toUpperCase()));
    }


    @RequestMapping(method = RequestMethod.GET, params = "pages_images", produces = "application/json")
    protected @ResponseBody
    String processTestWorkPages(@RequestParam("pages_images") Integer pagesImages) {
        return runTestService.getWorkPagesAsJson(pagesImages);
    }
}
