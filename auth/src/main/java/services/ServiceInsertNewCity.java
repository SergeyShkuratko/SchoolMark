package services;

import classes.City;
import classes.Region;
import dao.CityDAOImpl;
import dao.RegionDAOImpl;
import exceptions.CityDAOException;
import exceptions.RegionDAOException;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static core.dao.constants.DAOConstants.NULL_POINTER_DB;

public class ServiceInsertNewCity {

    private static final Logger logger = Logger.getLogger(ServiceInsertNewCity.class);

    @SuppressWarnings("all")
    public Map<String, String> insertNewRecord(String cityName, Integer regionNum) {
        CityDAOImpl cityDAO = new CityDAOImpl();
        Map<String, String> result = new HashMap<>();
        String errmessage = "Во время добавления региона в базу данных произошла ошибка ";
        try {
            Integer idinserted = cityDAO.insertCity(new City(NULL_POINTER_DB, regionNum, cityName));
            if(idinserted != NULL_POINTER_DB) {
                result.put("result", "Запись успешно добавлена");
                result.put("id", String.valueOf(idinserted));
                result.put("region_id", String.valueOf(regionNum));
                result.put("name", cityName);
                return result;
            }
            result.put("error", errmessage);
            return result;
        } catch (CityDAOException e) {
            logger.error(errmessage + " ("+e.getMessage()+")");
            result.put("error", errmessage + "("+e.getMessage()+")");
        }
        result.put("error", "Во время записи в базу данных произошла непредвиденная ошибка!");
        return result;
    }
}