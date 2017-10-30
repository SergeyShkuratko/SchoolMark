package services;

import classes.Region;
import studentmodule.dao.RegionDAOImpl;
import exceptions.RegionDAOException;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static core.dao.constants.DAOConstants.NULL_POINTER_DB;

public class ServiceInsertNewRegion {

    private static final Logger logger = Logger.getLogger(ServiceInsertNewRegion.class);

    public Map<String, String> insertNewRecord(String regionName, Integer regionNum) {
        RegionDAOImpl regionDAO = new RegionDAOImpl();
        Map<String, String> result = new HashMap<>();
        String errmessage = "Во время добавления региона в базу данных произошла ошибка ";
        try {
            Integer idinserted = regionDAO.insertRegion(new Region(NULL_POINTER_DB, regionNum, regionName));
            if(idinserted != NULL_POINTER_DB) {
                result.put("result", "Запись успешно добавлена");
                result.put("id", String.valueOf(idinserted));
                result.put("num", String.valueOf(regionNum));
                result.put("name", regionName);
                return result;
            }
            result.put("error", errmessage);
            return result;
        } catch (RegionDAOException e) {
            logger.error(errmessage + " ("+e.getMessage()+")");
            result.put("error", errmessage + "("+e.getMessage()+")");
        }
        result.put("error", "Во время записи в базу данных произошла непредвиденная ошибка!");
        return result;
    }
}