package servlets.admin;

import classes.dto.CityDTO;
import classes.dto.SchoolDTO;
import exceptions.CityDAOException;
import exceptions.SchoolDAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import services.CityService;
import services.SchoolService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SchoolServlet extends HttpServlet {
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

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pCityId = req.getParameter("cityId");
        String pSchoolId = req.getParameter("schoolId");
        String error = null;
        try {
            if (pSchoolId != null) {
                int schoolId = Integer.parseInt(pSchoolId);
                SchoolDTO school = schoolService.getSchoolById(schoolId);
                req.setAttribute("title", "Модификация школы "+school.getName()+"("+school.getSchoolType()+")");
                req.setAttribute("schoolId", schoolId);
                req.setAttribute("cityId", school.getCityId());
                req.setAttribute("cityName", school.getCityName());
                req.setAttribute("regionId", school.getRegionId());
                req.setAttribute("regionName", school.getRegionName());
                req.setAttribute("schoolTypes", schoolService.getAllSchoolTypes());
                req.setAttribute("schoolName", school.getName());
                req.setAttribute("schoolType", school.getSchoolTypeId());
                req.getRequestDispatcher("/school.jsp").forward(req, resp);
            } else if (pCityId != null) {
                CityDTO city = cityService.getCityDtoById(Integer.parseInt(pCityId));
                req.setAttribute("title", "Создание школы");
                req.setAttribute("schoolId", 0);
                req.setAttribute("cityId", city.getId());
                req.setAttribute("cityName", city.getName());
                req.setAttribute("regionId", city.getRegionId());
                req.setAttribute("regionName", city.getRegionName());
                req.setAttribute("schoolTypes", schoolService.getAllSchoolTypes());
                req.getRequestDispatcher("/school.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/admin/cabinet");
            }
        } catch (NumberFormatException | CityDAOException | SchoolDAOException e) {
            error = e.getMessage();
        }
        if (error != null) {
            req.setAttribute("errorText", error);
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pCityId = req.getParameter("cityId");
        String pCityName = req.getParameter("cityName");
        String pRegionId = req.getParameter("regionId");
        String pRegionName = req.getParameter("regionName");
        String pSchoolName = req.getParameter("schoolName");
        String pSchoolType = req.getParameter("schoolType");
        String pSchoolId = req.getParameter("schoolId");
        String pSchoolTypeName = req.getParameter("schoolTypeName");
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
                resp.sendRedirect(req.getContextPath() + "/admin/cabinet");
            } else {
                if(schoolId == 0) {
                    req.setAttribute("title", "Создание школы");
                }else{
                    SchoolDTO school = schoolService.getSchoolById(schoolId);
                    req.setAttribute("title", "Модификация школы "+school.getName()+"("+school.getSchoolType()+")");
                }
                req.setAttribute("cityId", cityId);
                req.setAttribute("schoolId", schoolId);
                req.setAttribute("regionId", regionId);
                req.setAttribute("cityName", pCityName);
                req.setAttribute("regionName", pRegionName);
                req.setAttribute("schoolName", pSchoolName);
                req.setAttribute("schoolTypes", schoolService.getAllSchoolTypes());
                req.setAttribute("schoolType", schoolType);
                req.setAttribute("message", "заданная школа уже существует!!");
                req.getRequestDispatcher("/school.jsp").forward(req, resp);
            }
        } catch (SchoolDAOException |
                NumberFormatException e) {
            error = e.getMessage();
            req.setAttribute("errorText", error);
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }

        if (error != null) {
            req.setAttribute("errorText", error);
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
