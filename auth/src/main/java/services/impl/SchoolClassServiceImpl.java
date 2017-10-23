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
    public boolean persistSchool(int schoolId, int classNum, String letter) throws SchoolClassDAOException {
        try {
            SchoolClassDTO schoolClass = new SchoolClassDTO(classNum, classNum + letter, schoolId);
            return schoolClassDAO.persistSchool(schoolClass);
        } catch (SchoolClassDAOException e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public SchoolClassDTO getSchoolClassById(int id) throws SchoolClassDAOException {
        SchoolClassDTO schoolClass = null;
        try {
            schoolClass = schoolClassDAO.getSchoolClassById(id);
        } catch (SchoolClassDAOException e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        return schoolClass;
    }
}
