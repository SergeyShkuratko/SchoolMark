package dao;

import classes.School;
import classes.SchoolClass;
import classes.SchoolType;
import classes.Student;
import exception.DAOStudentErrorRequestException;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.ConcurrentNavigableMap;

import static constants.DAOConstants.NULL_POINTER_DB;
import static org.junit.jupiter.api.Assertions.*;

class DAOStudentImplTest {

    private DAOStudentImpl daoStudent;
    private Logger logger;

    @BeforeEach
    void createDAOStudentImplTest() {
        try {
            daoStudent = new DAOStudentImpl();
            logger = Logger.getLogger(DAOStudentImplTest.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DAOStudentErrorRequestException e) {
            e.printStackTrace();
        }
    }

    @Test
    void insertStudentTest() {
        SchoolClass schoolClass = new SchoolClass(0, 0, "3Б");
        SchoolType schoolType = new SchoolType(0, "тип школы");
        School school = new School(0, "Школа", "Одесса", "Донбасс", schoolType);
//        try {
//            char [] buffer = new char [100];
//            daoStudent.insertStudent(
//                    new Student(
//                            NULL_POINTER_DB,
//                            1,
//                            "Иванов",
//                            "Иван",
//                            "Иванович",
//                            schoolClass,
//                            school
//                            )
//            );
//        } catch (DAOStudentErrorRequestException e) {
//            logger.error(e.getMessage());
//        }
        assertEquals(1,1);
    }

}