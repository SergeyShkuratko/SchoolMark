package services.impl;

import classes.SchoolClass;
import classes.SchoolType;
import classes.dto.SchoolDTO;
import exceptions.SchoolDAOException;
import interfaces.dao.SchoolsDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import services.SchoolService;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
    private static Logger logger = Logger.getLogger(SchoolServiceImpl.class);

    private SchoolsDAO schoolDAO;

    public SchoolServiceImpl(SchoolsDAO schoolDAO) {
        this.schoolDAO = schoolDAO;
    }

    @Override
    public SchoolDTO getSchoolById(int id) throws SchoolDAOException {
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
    public List<SchoolDTO> getSchoolsByCityId(int cityId) throws SchoolDAOException {
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

    @Override
    public boolean insertSchool(SchoolDTO school) throws SchoolDAOException {
        try {
            return schoolDAO.insertSchool(school);
        } catch (SchoolDAOException e) {
            logger.info(e);
            throw e;
        }
    }

    @Override
    public boolean updateSchool(SchoolDTO school) throws SchoolDAOException {
        try {
            return schoolDAO.updateSchool(school);
        } catch (SchoolDAOException e) {
            logger.info(e);
            throw e;
        }
    }

    @Override
    public List<SchoolType> getAllSchoolTypes() throws SchoolDAOException {
        try {
            return schoolDAO.getAllSchoolTypes();
        } catch (SchoolDAOException e) {
            logger.info(e);
            throw e;
        }
    }


}
