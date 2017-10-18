package dao;

import classes.SchoolClass;
import exception.DAOSchoolClassErrorRequestException;

import java.util.ArrayList;

public interface DAOSchoolClass {
    /**
     *
     * @param id
     * @return SchoolClass
     * @throws DAOSchoolClassErrorRequestException
     */
    SchoolClass getSchoolClassById(int id)
            throws DAOSchoolClassErrorRequestException;

    /**
     *
     * @param number
     * @return ArrayList<SchoolClass>
     * @throws DAOSchoolClassErrorRequestException
     */
    ArrayList<SchoolClass> getSchoolClassesByNumber(int number)
            throws DAOSchoolClassErrorRequestException;

    /**
     *
     * @param name
     * @return ArrayList<SchoolClass>
     * @throws DAOSchoolClassErrorRequestException
     */
    ArrayList<SchoolClass> getSchoolClassesByName(String name)
            throws DAOSchoolClassErrorRequestException;

    /**
     *
     * @param school_id
     * @return ArrayList<SchoolClass>
     * @throws DAOSchoolClassErrorRequestException
     */
    ArrayList<SchoolClass> getSchoolClassesBySchoolId(int school_id)
            throws DAOSchoolClassErrorRequestException;

    /**
     *
     * @param id
     * @return 1 if deleted, 0 if not found
     * @throws DAOSchoolClassErrorRequestException
     */
    int removeSchoolClassById(int id)
            throws DAOSchoolClassErrorRequestException;

    /**
     *
     * @param name
     * @return 1 if deleted, 0 if not found
     * @throws DAOSchoolClassErrorRequestException
     */
    int removeSchoolClassesByName(String name)
            throws DAOSchoolClassErrorRequestException;

    /**
     *
     * @param school_id
     * @return 1 if deleted, 0 if not found
     * @throws DAOSchoolClassErrorRequestException
     */
    int removeSchoolClassesBySchoolId(int school_id)
            throws DAOSchoolClassErrorRequestException;

    /**
     *
     * @param schoolClass
     * @return 1 - if inserted, 0 - if not
     * @throws DAOSchoolClassErrorRequestException
     */
    int insertSchoolClass(SchoolClass schoolClass)
            throws DAOSchoolClassErrorRequestException;

    /**
     *
     * @param schoolClasses
     * @return numerous of added Schools
     * @throws DAOSchoolClassErrorRequestException
     */
    int insertSchoolClasses(ArrayList<SchoolClass> schoolClasses)
            throws DAOSchoolClassErrorRequestException;

    /**
     *
     * @param schoolClass
     * @return 1 - if updated, 0 - if update failed
     * @throws DAOSchoolClassErrorRequestException
     */
    int updateSchoolClass(SchoolClass schoolClass)
            throws DAOSchoolClassErrorRequestException;

}
