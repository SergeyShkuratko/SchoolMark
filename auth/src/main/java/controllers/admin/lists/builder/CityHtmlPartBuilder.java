package controllers.admin.lists.builder;

import classes.City;
import classes.Region;
import dao.CityDAOImpl;
import dao.RegionDAOImpl;
import exceptions.CityDAOException;
import exceptions.RegionDAOException;
import org.apache.log4j.Logger;
import services.exceptions.ServicesAuthGetPropertyNotFoundException;
import services.exceptions.ServicesAuthTemplateNotFoundException;
import utils.files.FileContentGetter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CityHtmlPartBuilder {

    private static final Logger logger = Logger.getLogger(CityHtmlPartBuilder.class);
    private static String city_row_tpl;
    private static String dropdown_item_tpl;
    private ServletContext servletContext;

    public CityHtmlPartBuilder(ServletContext servletContext)
            throws IOException, ServicesAuthGetPropertyNotFoundException {
        this.servletContext = servletContext;
        try {
            InputStream is = getClass().getResourceAsStream("/auth-module-resources.properties");
            Properties props = new Properties();
            props.load(is);
            is.close();
            this.city_row_tpl = props.getProperty("amr_region_row_tpl");
            if (this.city_row_tpl == null) {
                String errMessage = "parameter amr_region_row_tpl not found into property file";
                logger.error(errMessage);
                throw new ServicesAuthGetPropertyNotFoundException(errMessage);
            }
            this.dropdown_item_tpl = props.getProperty("amr_dropdown_item_tpl");
            if (this.dropdown_item_tpl == null) {
                String errMessage = "parameter amr_dropdown_item_tpl not found into property file";
                logger.error(errMessage);
                throw new ServicesAuthGetPropertyNotFoundException(errMessage);
            }
        } catch (NullPointerException npe) {
            throw new ServicesAuthGetPropertyNotFoundException(
                    "not found : InputStream is = getClass().getResourceAsStream(/auth-module-resources.properties)"
            );
        }

    }

    public String getRegionsDropdownList()
            throws ServicesAuthTemplateNotFoundException, RegionDAOException {
        try {
            InputStream in = this.servletContext.getResourceAsStream(this.dropdown_item_tpl);
            String template = new FileContentGetter().getFileContent(in);
            StringBuilder result = new StringBuilder();
            RegionDAOImpl regionDAO = new RegionDAOImpl();
            List<Region> regions = regionDAO.getAll();
            if (regions == null) {
                return "";
            }
            for(Region region : regions) {
                result.append(
                        template
                                .replace("[[++href++]]", "/SM/admin/cities?region=" + region.getId())
                                .replace("[[++name++]]", String.valueOf(region.getName()))
                );
            }
            return result.toString();
        } catch(IOException x) {
            throw new ServicesAuthTemplateNotFoundException(x.getMessage());
        }
    }

    public String getRegionsDropdownOptionsList(Region currentRegion) {
        try {
            String template = "<option value='[[++value++]]'>[[++name++]]</option>";
            StringBuilder result = new StringBuilder();
            RegionDAOImpl regionDAO = new RegionDAOImpl();
            List<Region> regions = null;
            if(currentRegion != null) {
                regions = new ArrayList<>();
                regions.add(currentRegion);
            }
            else {
                regions = regionDAO.getAll();
                if (regions == null) {
                    return "";
                }
            }
            for(Region region : regions) {
                result.append(
                        template
                                .replace("[[++value++]]", String.valueOf(region.getId()))
                                .replace("[[++name++]]", String.valueOf(region.getName()))
                );
            }
            return result.toString();
        } catch (RegionDAOException e) {
            logger.error(e);
            return "";
        }
    }

    public String getContentForAllCities(Region currentRegion)
            throws CityDAOException, ServicesAuthTemplateNotFoundException {
        try {
            InputStream in = this.servletContext.getResourceAsStream(this.city_row_tpl);
            String template = new FileContentGetter().getFileContent(in);
            StringBuilder result = new StringBuilder();
            CityDAOImpl cityDAO = new CityDAOImpl();
            List<City> cities = cityDAO.getAllByRegion(currentRegion);
            if (cities == null) {
                return "";
            }
            for(City city : cities) {
                result.append(
                        template
                                .replace("[[++id++]]", String.valueOf(city.getId()))
                                .replace("[[++num++]]", String.valueOf(city.getRegionId()))
                                .replace("[[++name++]]", city.getName())
                );
            }
            return result.toString();
        } catch(IOException x) {
            throw new ServicesAuthTemplateNotFoundException(x.getMessage());
        }
    }

    public Region getCurrentRegion(HttpServletRequest req) {
        if(req.getParameter("region") != null) {
            RegionDAOImpl regionDAO = new RegionDAOImpl();
            try {
                return regionDAO.getById(Integer.valueOf(req.getParameter("region")));

            } catch (RegionDAOException e) {
                logger.error(e);
                return null;
            }
        }
        return null;
    }

}
