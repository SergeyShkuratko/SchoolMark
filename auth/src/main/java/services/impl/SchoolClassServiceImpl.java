package services.impl;

import classes.dto.SchoolClassDTO;
import dao.SchoolClassDAOImpl;
import exceptions.SchoolClassDAOException;
import interfaces.dao.SchoolClassDAO;
import org.apache.log4j.Logger;
import services.SchoolClassService;

public class SchoolClassServiceImpl implements SchoolClassService {

    private static SchoolClassDAO schoolClassDAO = new SchoolClassDAOImpl();
    private static Logger logger = Logger.getLogger(SchoolClassServiceImpl.class);

    @Override
    public boolean add(int schoolId, int classNum, String letter) throws SchoolClassDAOException {
        boolean result = false;
        try {
            SchoolClassDTO schoolClass = new SchoolClassDTO(classNum, classNum + letter, schoolId);
            schoolClassDAO.add(schoolClass);
            result = true;
        } catch (SchoolClassDAOException e) {
            logger.info(e);
            throw e;
        }
        return result;
    }
}
