package services;

import classes.Region;
import dao.RegionDAOImpl;
import exceptions.RegionDAOException;
import org.apache.log4j.Logger;
import services.exceptions.ServicesAuthGetProperyNotFoundException;
import services.exceptions.ServicesAuthTemplateNotFoundException;
import utils.files.FileContentGetter;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class ServiceDAORegion {

    private static final Logger logger = Logger.getLogger(ServiceDAORegion.class);
    private static String region_row_tpl;
    private ServletContext servletContext;

    public ServiceDAORegion (ServletContext servletContext)
            throws IOException, ServicesAuthGetProperyNotFoundException {
        this.servletContext = servletContext;
        try {
            InputStream is = getClass().getResourceAsStream("/auth-module-resources.properties");
            Properties props = new Properties();
            props.load(is);
            is.close();
            this.region_row_tpl = props.getProperty("amr_region_row_tpl");
            if (this.region_row_tpl == null) {
                String errMessage = "parameter amr_region_row_tpl not found into property file";
                logger.error(errMessage);
                throw new ServicesAuthGetProperyNotFoundException(errMessage);
            }
        } catch (NullPointerException npe) {
            throw new ServicesAuthGetProperyNotFoundException(
                    "not found : InputStream is = getClass().getResourceAsStream(/auth-module-resources.properties)"
            );
        }

    }

    public String getContentForAllRegions()
            throws RegionDAOException, ServicesAuthTemplateNotFoundException {
        try {
            InputStream in = this.servletContext.getResourceAsStream(this.region_row_tpl);
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
                        .replace("[[++id++]]", String.valueOf(region.getId()))
                        .replace("[[++num++]]", String.valueOf(region.getNum()))
                        .replace("[[++name++]]", region.getName())
                );
            }
            return result.toString();
        } catch(IOException x) {
            throw new ServicesAuthTemplateNotFoundException(x.getMessage());
        }
    }

}
