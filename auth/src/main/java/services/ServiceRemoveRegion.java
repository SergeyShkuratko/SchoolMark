package services;

import classes.Region;
import student.dao.RegionDAOImpl;
import exceptions.RegionDAOException;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ServiceRemoveRegion {

    private static final Logger logger = Logger.getLogger(ServiceInsertNewRegion.class);

    public Map<String, String> removeRecordById(Integer regionId) {
        RegionDAOImpl regionDAO = new RegionDAOImpl();
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