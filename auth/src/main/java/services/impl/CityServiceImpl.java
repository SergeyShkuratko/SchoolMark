package services.impl;

import classes.dto.CityDTO;
import dao.CityDAOImpl;
import exceptions.CityDAOException;
import interfaces.dao.CityDAO;
import org.apache.log4j.Logger;
import services.CityService;

public class CityServiceImpl implements CityService{
    private CityDAO cityDAO = new CityDAOImpl();
    private static Logger logger = Logger.getLogger(SchoolServiceImpl.class);

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
