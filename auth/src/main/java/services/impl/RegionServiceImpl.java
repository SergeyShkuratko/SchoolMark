package services.impl;

import classes.Region;
import exceptions.RegionDAOException;
import interfaces.dao.RegionDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import services.RegionService;

import java.util.HashMap;
import java.util.Map;

import static core.dao.constants.DAOConstants.NULL_POINTER_DB;

@Service
public class RegionServiceImpl implements RegionService {
    private static final Logger logger = Logger.getLogger(RegionServiceImpl.class);

    private RegionDAO regionDAO;

    public RegionServiceImpl(RegionDAO regionDAO) {
        this.regionDAO = regionDAO;
    }

    @Override
    public Map<String, String> insertNewRecord(String regionName, Integer regionNum) {
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

    @Override
    public Map<String, String> removeRecordById(Integer regionId) {
        Map<String, String> result = new HashMap<>();
        String errmessage = "Во время удаления региона произошла ошибка ";
        try {
            Integer flag = regionDAO.removeRegion(new Region(regionId, 0, null));
            if(flag == 1) {
                result.put("result", "Запись успешно удалена");
                return result;
            }
            result.put("error", errmessage);
            return result;
        } catch (RegionDAOException e) {
            logger.error(errmessage + " ("+e.getMessage()+")");
            result.put("error", errmessage + "("+e.getMessage()+")");
        }
        result.put("error", "Во время удаления региона произошла непредвиденная ошибка!");
        return result;
    }
}
