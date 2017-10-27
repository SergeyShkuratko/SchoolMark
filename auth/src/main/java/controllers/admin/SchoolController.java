package controllers.admin;

import classes.dto.CityDTO;
import classes.dto.SchoolDTO;
import exceptions.CityDAOException;
import exceptions.SchoolDAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.CityService;
import services.SchoolService;

import javax.servlet.http.HttpServlet;

import static utils.Settings.*;

@Controller
public class SchoolController extends HttpServlet {
    private CityService cityService;
    private SchoolService schoolService;

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @Autowired
    public void setSchoolService(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @RequestMapping(value = "/admin/school", method = RequestMethod.GET)
    protected ModelAndView doGet(@RequestParam(value = "cityId", required = false) String pCityId,
                                 @RequestParam(value = "schoolId", required = false) String pSchoolId) {
        ModelAndView mv = new ModelAndView();
        String error = null;
        try {
            if (pSchoolId != null) {
                int schoolId = Integer.parseInt(pSchoolId);
                SchoolDTO school = schoolService.getSchoolById(schoolId);
                mv.addObject("title", "Модификация школы " + school.getName() + "(" + school.getSchoolType() + ")");
                mv.addObject("schoolId", schoolId);
                mv.addObject("cityId", school.getCityId());
                mv.addObject("cityName", school.getCityName());
                mv.addObject("regionId", school.getRegionId());
                mv.addObject("regionName", school.getRegionName());
                mv.addObject("schoolTypes", schoolService.getAllSchoolTypes());
                mv.addObject("schoolName", school.getName());
                mv.addObject("schoolType", school.getSchoolTypeId());
                mv.setViewName("school");
            } else if (pCityId != null) {
                CityDTO city = cityService.getCityDtoById(Integer.parseInt(pCityId));
                mv.addObject("title", "Создание школы");
                mv.addObject("schoolId", 0);
                mv.addObject("cityId", city.getId());
                mv.addObject("cityName", city.getName());
                mv.addObject("regionId", city.getRegionId());
                mv.addObject("regionName", city.getRegionName());
                mv.addObject("schoolTypes", schoolService.getAllSchoolTypes());
                mv.setViewName(SCHOOL_JSP);
            } else {
                mv.setViewName("redirect:" + "/admin/cabinet");
            }
        } catch (NumberFormatException | CityDAOException | SchoolDAOException e) {
            error = e.getMessage();
        }
        if (error != null) {
            mv.addObject(ERROR_ATTR, error);
            mv.setViewName(ERROR_JSP);
        }
        return mv;
    }

    @RequestMapping(value = "/admin/school", method = RequestMethod.POST)
    protected ModelAndView doPost(@RequestParam("cityId") String pCityId,
                                  @RequestParam("cityName") String pCityName,
                                  @RequestParam("regionId") String pRegionId,
                                  @RequestParam("regionName") String pRegionName,
                                  @RequestParam("schoolName") String pSchoolName,
                                  @RequestParam("schoolType") String pSchoolType,
                                  @RequestParam("schoolId") String pSchoolId,
                                  @RequestParam("schoolTypeName") String pSchoolTypeName) {
        ModelAndView mv = new ModelAndView();
        String error = null;
        try {
            int schoolId = Integer.parseInt(pSchoolId);
            int cityId = Integer.parseInt(pCityId);
            int regionId = Integer.parseInt(pRegionId);
            int schoolType = Integer.parseInt(pSchoolType);
            SchoolDTO schoolDTO = new SchoolDTO(schoolId, pSchoolName, regionId, cityId, pRegionName, pCityName,
                    pSchoolTypeName, schoolType);

            boolean result;
            if (schoolId == 0)
                result = schoolService.insertSchool(schoolDTO);
            else
                result = schoolService.updateSchool(schoolDTO);

            if (result) {
                mv.setViewName("redirect:" + "/admin/cabinet");
            } else {
                if (schoolId == 0) {
                    mv.addObject("title", "Создание школы");
                } else {
                    SchoolDTO school = schoolService.getSchoolById(schoolId);
                    mv.addObject("title", "Модификация школы " + school.getName() + "(" + school.getSchoolType() + ")");
                }
                mv.addObject("cityId", cityId);
                mv.addObject("schoolId", schoolId);
                mv.addObject("regionId", regionId);
                mv.addObject("cityName", pCityName);
                mv.addObject("regionName", pRegionName);
                mv.addObject("schoolName", pSchoolName);
                mv.addObject("schoolTypes", schoolService.getAllSchoolTypes());
                mv.addObject("schoolType", schoolType);
                mv.addObject("message", "заданная школа уже существует!!");
                mv.setViewName(SCHOOL_JSP);
            }
        } catch (SchoolDAOException |
                NumberFormatException e) {
            error = e.getMessage();
            mv.addObject(ERROR_ATTR, error);
            mv.setViewName(ERROR_JSP);
        }

        if (error != null) {
            mv.addObject(ERROR_ATTR, error);
            mv.setViewName(ERROR_JSP);
        }
        return mv;
    }
}
