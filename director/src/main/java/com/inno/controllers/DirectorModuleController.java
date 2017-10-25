package com.inno.controllers;

import com.inno.service.TestStatisticService;
import com.inno.service.TestStatisticServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Controller
@RequestMapping("/director")
public class DirectorModuleController {
    @Autowired
    private TestStatisticService testStatisticService;

    @RequestMapping(value = "/testlist", method = RequestMethod.GET)
    public ModelAndView getTestListView(@RequestParam(name = "groupByOrganization", required=false) String groupByOrganization,
                                        @RequestParam(name = "dateFrom", required=false) String dateFromStr,
                                        @RequestParam(name = "dateTo", required=false) String dateToStr)
    {
        LocalDate dateFrom = parseDate(dateFromStr);
        LocalDate dateTo = parseDate(dateToStr);
        ModelAndView modelAndView = new ModelAndView("director-test-list");
        if (!"on".equals(groupByOrganization)) {
            modelAndView.addObject("tests", testStatisticService.getTestsStatistic(dateFrom, dateTo));
        } else {
            modelAndView.addObject("teacherTestsMap",
                    testStatisticService.getTestsStatisticGroupedByOwner(dateFrom, dateTo));
        }
        return modelAndView;
    }

    private LocalDate parseDate(String dateStr) {
        if (dateStr == null) {
            return null;
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        try {
            return LocalDate.parse(dateStr, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @RequestMapping(value = "/testview", method = RequestMethod.GET)
    public ModelAndView getTestView(@RequestParam("testId") int testId){
        return new ModelAndView("director-test-view")
                .addObject("testAndWorkInfo", testStatisticService.getTestAndWorksInfo(testId));
    }
}
