package dao;

import classes.School;
import exception.DAOSchoolErrorRequestException;
import java.util.ArrayList;


public interface DAOSchool {

    /**
     *
     * @param id
     * @return School
     * @throws DAOSchoolErrorRequestException
     */
    School getSchoolById(int id) throws DAOSchoolErrorRequestException;

    /**
     *
     * @param id
     * @return 1 if deleted, 0 if not found
     * @throws DAOSchoolErrorRequestException
     */
    int removeSchoolById(int id) throws DAOSchoolErrorRequestException;

    /**
     *
     * @param School
     * @return 1 - if inserted, 0 - if not
     * @throws DAOSchoolErrorRequestException
     */
    int insertSchool(School School) throws DAOSchoolErrorRequestException;

    /**
     *
     * @param Schools
     * @return numerous of added Schools
     * @throws DAOSchoolErrorRequestException
     */
    int insertSchools(ArrayList<School> Schools) throws DAOSchoolErrorRequestException;

    /**
     *
     * @param School
     * @return 1 - if updated, 0 - if update failed
     * @throws DAOSchoolErrorRequestException
     */
    int updateSchool(School School) throws DAOSchoolErrorRequestException;

    /**
     *
     * @param name
     * @return
     * @throws DAOSchoolErrorRequestException
     */
    int removeSchoolByName(String name) throws DAOSchoolErrorRequestException;

    /**
     *
     * @param region
     * @return
     * @throws DAOSchoolErrorRequestException
     */
    int removeSchoolsByRegion(String region) throws DAOSchoolErrorRequestException;

    /**
     *
     * @param city
     * @return
     * @throws DAOSchoolErrorRequestException
     */
    int removeSchoolsByCity(String city) throws DAOSchoolErrorRequestException;

    /**
     *
     * @param school_type_id
     * @return
     * @throws DAOSchoolErrorRequestException
     */
    int removeSchoolsBySchoolTypeId(int school_type_id) throws DAOSchoolErrorRequestException;

    /**
     *
     * @param name
     * @return
     * @throws DAOSchoolErrorRequestException
     */
    ArrayList<School> getSchoolsByName(String name) throws DAOSchoolErrorRequestException;

    /**
     *
     * @param region
     * @return
     * @throws DAOSchoolErrorRequestException
     */
    ArrayList<School> getSchoolsByRegion(String region) throws DAOSchoolErrorRequestException;

    /**
     *
     * @param city
     * @return
     * @throws DAOSchoolErrorRequestException
     */
    ArrayList<School> getSchoolsByCity(String city) throws DAOSchoolErrorRequestException;

    /**
     *
     * @param school_type_id
     * @return
     * @throws DAOSchoolErrorRequestException
     */
    ArrayList<School> getSchoolsBySchoolTypeId(String school_type_id) throws DAOSchoolErrorRequestException;
}

