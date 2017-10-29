package services.impl;

import classes.dto.SchoolClassDTO;
import exceptions.SchoolClassDAOException;
import interfaces.dao.SchoolClassDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import services.SchoolClassService;

@Service
public class SchoolClassServiceImpl implements SchoolClassService {
    private static Logger logger = Logger.getLogger(SchoolClassServiceImpl.class);

    private static SchoolClassDAO schoolClassDAO;

    public SchoolClassServiceImpl(SchoolClassDAO schoolClassDAO) {
        this.schoolClassDAO = schoolClassDAO;
    }

    @Override
    public boolean add(int schoolId, int classNum, String letter) throws SchoolClassDAOException {
        try {
            SchoolClassDTO schoolClass = new SchoolClassDTO(classNum, classNum + letter, schoolId);
            return schoolClassDAO.persistSchool(schoolClass);
        } catch (SchoolClassDAOException e) {
            logger.info(e);
            throw e;
        }
    }
}
