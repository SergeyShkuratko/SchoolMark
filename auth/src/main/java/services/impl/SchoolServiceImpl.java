package services.impl;

import classes.SchoolClass;
import classes.dto.SchoolDTO;
import dao.SchoolDAOImpl;
import exceptions.SchoolDAOException;
import interfaces.dao.SchoolsDAO;
import org.apache.log4j.Logger;
import services.SchoolService;

import java.util.List;

public class SchoolServiceImpl implements SchoolService {
    private static SchoolsDAO schoolDAO = new SchoolDAOImpl();
    private static Logger logger = Logger.getLogger(SchoolServiceImpl.class);

    @Override
    public SchoolDTO getSchool(int id) throws SchoolDAOException {
        SchoolDTO school = null;
        try {
            school = schoolDAO.getById(id);
        } catch (SchoolDAOException e) {
            logger.info(e);
            throw e;
        }
        return school;
    }

    @Override
    public List<SchoolDTO> getAllSchools() throws SchoolDAOException {
        List<SchoolDTO> schools = null;
        try {
            schools = schoolDAO.getAll();
        } catch (SchoolDAOException e) {
            logger.info(e);
            throw e;
        }
        return schools;
    }

    @Override
    public List<SchoolDTO> getSchoolsByLocation(int cityId) throws SchoolDAOException {
        List<SchoolDTO> schools = null;
        try {
            schools = schoolDAO.getAllSchoolsInCity(cityId);
        } catch (SchoolDAOException e) {
            logger.info(e);
            throw e;
        }
        return schools;
    }

    @Override
    public List<SchoolClass> getClassesBySchoolId(int schoolId) throws SchoolDAOException {
        List<SchoolClass> classes = null;
        try {
            classes = schoolDAO.getAllClasses(schoolId);
        } catch (SchoolDAOException e) {
            logger.info(e);
            throw e;
        }
        return classes;
    }


}
