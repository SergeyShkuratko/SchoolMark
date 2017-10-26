package services;

import classes.City;
import controller.dao.CityDAOImpl;
import controller.dao.RegionDAOImpl;
import exceptions.CityDAOException;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ServiceRemoveCity {

    private static final Logger logger = Logger.getLogger(ServiceRemoveCity.class);

    public Map<String, String> removeRecordById(Integer cityId) {
        CityDAOImpl cityDAO = new CityDAOImpl();
        Map<String, String> result = new HashMap<>();
        String errmessage = "Во время удаления города произошла ошибка ";
        try {
            Integer flag = cityDAO.removeCity(new City(cityId, 0, null));
            if(flag == 1) {
                result.put("result", "Запись успешно удалена");
                return result;
            }
            result.put("error", errmessage);
            return result;
        } catch (CityDAOException e) {
            logger.error(errmessage + " ("+e.getMessage()+")");
            result.put("error", errmessage + "("+e.getMessage()+")");
        }
        result.put("error", "Во время удаления города произошла непредвиденная ошибка!");
        return result;
    }
}