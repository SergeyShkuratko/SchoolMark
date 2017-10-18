package dao;

import classes.Student;
import exception.DAOStudentErrorRequestException;

import java.sql.SQLException;
import java.util.ArrayList;


public interface DAOStudent {

    /**
     *
     * @param id
     * @return Student
     * @throws DAOStudentErrorRequestException
     */
    Student getStudentById(int id) throws DAOStudentErrorRequestException;

    /**
     *
     * @param userid
     * @return Student
     * @throws DAOStudentErrorRequestException
     */
    Student getStudentByUserId(int userid) throws DAOStudentErrorRequestException;

    /**
     *
     * @param lastName
     * @return ArrayList<Student>
     * @throws DAOStudentErrorRequestException
     */
    ArrayList<Student> getStudentsByLastName(String lastName) throws DAOStudentErrorRequestException;

    /**
     *
     * @param firstName
     * @return ArrayList<Student>
     * @throws DAOStudentErrorRequestException
     */
    ArrayList<Student> getStudentsByFirstName(String firstName) throws DAOStudentErrorRequestException;

    /**
     *
     * @param patronymic
     * @return ArrayList<Student>
     * @throws DAOStudentErrorRequestException
     */
    ArrayList<Student> getStudentsByPatronymic(String patronymic) throws DAOStudentErrorRequestException;

    /**
     *
     * @param schoolClassId
     * @return ArrayList<Student>
     * @throws DAOStudentErrorRequestException
     */
    ArrayList<Student> getStudentsBySchoolClassId(int schoolClassId) throws DAOStudentErrorRequestException;

    /**
     *
     * @param id
     * @return 1 if deleted, 0 if not found
     * @throws DAOStudentErrorRequestException
     */
    int removeStudentById(int id) throws DAOStudentErrorRequestException;

    /**
     *
     * @param user_id
     * @return 1 if deleted, if not found
     * @throws DAOStudentErrorRequestException
     */
    int removeStudentByUserId(int user_id) throws DAOStudentErrorRequestException;

    /**
     *
     * @param lastName
     * @return numerous of deleted
     * @throws DAOStudentErrorRequestException
     */
    int removeStudentsByLastName(String lastName) throws DAOStudentErrorRequestException;

    /**
     *
     * @param firstName
     * @return numerous of deleted
     * @throws DAOStudentErrorRequestException
     */
    int removeStudentsByFirstName(String firstName) throws DAOStudentErrorRequestException;

    /**
     *
     * @param patronymic
     * @return numerous of deleted
     * @throws DAOStudentErrorRequestException
     */
    int removeStudentsByPatronymic(String patronymic) throws DAOStudentErrorRequestException;

    /**
     *
     * @param schoolClassId
     * @return numerous of deleted
     * @throws DAOStudentErrorRequestException
     */
    int removeStudentsBySchoolClassId(int schoolClassId) throws DAOStudentErrorRequestException;

    /**
     *
     * @param student
     * @return 1 - if inserted, 0 - if not
     * @throws DAOStudentErrorRequestException
     */
    int insertStudent(Student student) throws DAOStudentErrorRequestException;

    /**
     *
     * @param students
     * @return numerous of added students
     * @throws DAOStudentErrorRequestException
     */
    int insertStudents(ArrayList<Student> students) throws DAOStudentErrorRequestException;

    /**
     *
     * @param student
     * @return 1 - if updated, 0 - if update failed
     * @throws DAOStudentErrorRequestException
     */
    int updateStudent(Student student) throws DAOStudentErrorRequestException;
}
