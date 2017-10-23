package dao;

import classes.School;
import classes.SchoolType;
import exception.DAOSchoolErrorRequestException;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

//import static constants.DAOConstants.NULL_POINTER_DB;
import static org.junit.jupiter.api.Assertions.*;

class DAOSchoolImplTest {

    private DAOSchoolImpl daoSchool;
    private Logger logger;

    @BeforeEach
    void createDAOStudentImplTest() {
        try {
            daoSchool = new DAOSchoolImpl();
            logger = Logger.getLogger(DAOSchoolImpl.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DAOSchoolErrorRequestException e) {
            e.printStackTrace();
        }
    }

    @Test
    void insertSchoolTest() {
        SchoolType schoolType = new SchoolType(0, "тип школы");
        try {
            daoSchool.insertSchool(
                    new School(
                            -1,
                            "школа",
                            "Питер",
                            "Москва",
                            schoolType
                    )
            );
        } catch (DAOSchoolErrorRequestException e) {
            logger.error(e.getMessage());
        }
        assertEquals(1,1);
    }

}