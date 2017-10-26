package servlets.admin.lists;

import classes.Region;
import exceptions.CityDAOException;
import exceptions.RegionDAOException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.ServiceDAOCity;
import services.exceptions.ServicesAuthGetPropertyNotFoundException;
import services.exceptions.ServicesAuthTemplateNotFoundException;
import utils.jsp.GetJspContent;
import utils.properties.PropertyValueGetter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.constants.ServletConstants.BASE_JSP_PATH;

@Controller
public class CitiesListServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(CitiesListServlet.class);

    @RequestMapping(value = "/admin/cities", method = RequestMethod.GET)
    public void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        RequestDispatcher dispatcher =
                req.getRequestDispatcher(BASE_JSP_PATH);
        try {
            try {
                ServiceDAOCity serviceDAOCity = new ServiceDAOCity(req.getServletContext());
                Region currentRegion = serviceDAOCity.getCurrentRegion(req);
                PropertyValueGetter propertyValueGetter = new PropertyValueGetter();
                String jsp = propertyValueGetter.getPropertyByKey("amr_cities_list_jsp_path");
                req.setAttribute("dropdownTitle",
                        currentRegion != null ? currentRegion.getName() : "Выберите регион");
                req.setAttribute("pagetitle", "Список городов");
                req.setAttribute("modaltitle", "Добавить город");
                req.setAttribute("table", serviceDAOCity.getContentForAllCities(currentRegion));
                req.setAttribute("regionslist", serviceDAOCity.getRegionsDropdownList());
                req.setAttribute("regionsOptionsList", serviceDAOCity.getRegionsDropdownOptionsList(currentRegion));
                req.setAttribute("content", new GetJspContent(req, resp).getContent(jsp));
                dispatcher.forward(req, resp);
            } catch (ServicesAuthGetPropertyNotFoundException
                    | CityDAOException
                    | ServicesAuthTemplateNotFoundException
                    | RegionDAOException e) {
                logger.error(e.getMessage());
                req.setAttribute("error", e.getMessage());
                dispatcher.forward(req, resp);
            }
        } catch (IOException | ServletException e) {
            logger.error(e);
        }
    }
}
