package services.impl;

import classes.City;
import classes.dto.CityDTO;
import exceptions.CityDAOException;
import interfaces.dao.CityDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import services.CityService;

import java.util.HashMap;
import java.util.Map;

import static core.dao.constants.DAOConstants.NULL_POINTER_DB;

@Service
public class CityServiceImpl implements CityService {
    private static final Logger logger = Logger.getLogger(CityServiceImpl.class);

    private CityDAO cityDAO;

    public CityServiceImpl(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    @Override
    public Map<String, String> insertNewRecord(String cityName, Integer regionNum) {
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

    @Override
    public Map<String, String> removeRecordById(Integer cityId) {
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

    @Override
    public CityDTO getCityDtoById(int id) throws CityDAOException {
        try {
            return cityDAO.getCityDtoById(id);
        }catch (CityDAOException e){
            logger.error(e);
            throw e;
        }
    }
}
