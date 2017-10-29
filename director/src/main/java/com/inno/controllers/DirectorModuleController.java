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
    private DateConverter dateConverter;

    @Autowired
    public void setDateConverter(DateConverter dateConverter) {
        this.dateConverter = dateConverter;
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
            dateFromStr = getFirstSchoolDay();
        }
        LocalDate dateFrom = dateConverter.parseStringToLocalDate(dateFromStr);

        LocalDate dateTo;
        if (dateToStr == null) {
            dateTo = LocalDate.now();
        } else {
            dateTo = dateConverter.parseStringToLocalDate(dateToStr);
        }

        ModelAndView modelAndView = new ModelAndView("director-test-list");
        modelAndView.addObject("dateFrom", dateConverter.formatLocalDateToString(dateFrom));
        modelAndView.addObject("dateTo", dateConverter.formatLocalDateToString(dateTo));
        int userId = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        if (!"on".equals(groupByOrganization)) {
            modelAndView.addObject("tests",
                    testStatisticService.getTestsStatisticByUserId(userId, dateFrom, dateTo));
        } else {
            modelAndView.addObject("teacherTestsMap",
                    testStatisticService.getTestsStatisticGroupedByOwner(userId, dateFrom, dateTo));
        }
        return modelAndView;
    }

    private String getFirstSchoolDay() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        if (month < 9) {
            --year;
        }
        return "01.09." + (year);
    }

    @RequestMapping(value = "/director-test-view", method = RequestMethod.GET)
    public ModelAndView getTestView(@RequestParam("testId") int testId) {
        return new ModelAndView("director-test-view")
                .addObject("testAndWorkInfo", testStatisticService.getTestAndWorksInfo(testId));
    }
}
