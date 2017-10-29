package controllers.admin.lists;

import classes.Region;
import exceptions.CityDAOException;
import exceptions.RegionDAOException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import controllers.admin.lists.builder.CityHtmlPartBuilder;
import exceptions.ServicesAuthGetPropertyNotFoundException;
import exceptions.ServicesAuthTemplateNotFoundException;
import utils.jsp.GetJspContent;
import utils.properties.PropertyValueGetter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controllers.constants.ServletConstants.BASE_JSP_PATH;

@Controller
public class CitiesListController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(CitiesListController.class);

    @RequestMapping(value = "/admin/cities", method = RequestMethod.GET)
    public void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        RequestDispatcher dispatcher =
                req.getRequestDispatcher(BASE_JSP_PATH);
        try {
            try {
                CityHtmlPartBuilder cityHtmlPartBuilder = new CityHtmlPartBuilder(req.getServletContext());
                Region currentRegion = cityHtmlPartBuilder.getCurrentRegion(req);
                PropertyValueGetter propertyValueGetter = new PropertyValueGetter();
                String jsp = propertyValueGetter.getPropertyByKey("amr_cities_list_jsp_path");
                req.setAttribute("dropdownTitle",
                        currentRegion != null ? currentRegion.getName() : "Выберите регион");
                req.setAttribute("pagetitle", "Список городов");
                req.setAttribute("modaltitle", "Добавить город");
                req.setAttribute("table", cityHtmlPartBuilder.getContentForAllCities(currentRegion));
                req.setAttribute("regionslist", cityHtmlPartBuilder.getRegionsDropdownList());
                req.setAttribute("regionsOptionsList", cityHtmlPartBuilder.getRegionsDropdownOptionsList(currentRegion));
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