package servlets.admin.lists;

import classes.Region;
import exceptions.CityDAOException;
import exceptions.RegionDAOException;
import org.apache.log4j.Logger;
import servlets.admin.lists.builder.CityHtmlPartBuilder;
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

@WebServlet(urlPatterns = "/admin/cities")
public class CitiesListServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(CitiesListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher =
                this.getServletContext().getRequestDispatcher(BASE_JSP_PATH);
        try {
            CityHtmlPartBuilder cityHtmlPartBuilder = new CityHtmlPartBuilder(getServletContext());
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
        } catch (ServicesAuthGetPropertyNotFoundException e) {
            logger.error(e.getMessage());
            req.setAttribute("error", e.getMessage());
            dispatcher.forward(req, resp);
        } catch (CityDAOException e) {
            logger.error(e.getMessage());
            req.setAttribute("error", e.getMessage());
            dispatcher.forward(req, resp);
        } catch (ServicesAuthTemplateNotFoundException e) {
            logger.error(e.getMessage());
            req.setAttribute("error", e.getMessage());
            dispatcher.forward(req, resp);
        } catch (RegionDAOException e) {
            logger.error(e.getMessage());
            req.setAttribute("error", e.getMessage());
            dispatcher.forward(req, resp);
        }

    }
}
