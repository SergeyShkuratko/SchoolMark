package com.inno.controllers;

import com.inno.service.TestStatisticService;
import com.inno.utils.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Calendar;


@Controller
public class DirectorModuleController {

    private TestStatisticService testStatisticService;
    private DateConverter dc;
    private int userId = 11;

    @Autowired
    public void setDc(DateConverter dc) {
        this.dc = dc;
    }

    @Autowired
    public void setTestStatisticService(TestStatisticService testStatisticService) {
        this.testStatisticService = testStatisticService;
    }

    @RequestMapping(value = "/director-test-list", method = RequestMethod.GET)
    public ModelAndView getTestListView(@RequestParam(name = "groupByOrganization", required = false) String groupByOrganization,
                                        @RequestParam(name = "dateFrom", required = false) String dateFromStr,
                                        @RequestParam(name = "dateTo", required = false) String dateToStr) {

        if (dateFromStr == null) {
            dateFromStr = "01.01."+Calendar.getInstance().get(Calendar.YEAR);
        }
        LocalDate dateFrom = dc.parseStringToLocalDate(dateFromStr);

        LocalDate dateTo;
        if (dateToStr == null) {
            dateTo = LocalDate.now();
        } else {
            dateTo = dc.parseStringToLocalDate(dateToStr);
        }

        ModelAndView modelAndView = new ModelAndView("director-test-list");
        modelAndView.addObject("dateFrom", dc.formatLocalDateToString(dateFrom));
        modelAndView.addObject("dateTo", dc.formatLocalDateToString(dateTo));

        if (!"on".equals(groupByOrganization)) {
            modelAndView.addObject("tests",
                    testStatisticService.getTestsStatistic(userId, dateFrom, dateTo));
        } else {
            modelAndView.addObject("teacherTestsMap",
                    testStatisticService.getTestsStatisticGroupedByOwner(userId, dateFrom, dateTo));
        }
        return modelAndView;
    }

    @RequestMapping(value = "/director-test-view", method = RequestMethod.GET)
    public ModelAndView getTestView(@RequestParam("testId") int testId) {
        return new ModelAndView("director-test-view")
                .addObject("testAndWorkInfo", testStatisticService.getTestAndWorksInfo(testId));
    }
}
