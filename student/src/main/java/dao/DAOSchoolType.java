package dao;

import classes.SchoolType;
import classes.Student;
import exception.DAOSchoolTypeErrorRequest;

import java.util.ArrayList;

public interface DAOSchoolType {
    /**
     *
     * @param id
     * @return SchoolType
     * @throws DAOSchoolTypeErrorRequest
     */
    SchoolType getSchoolTypeById(int id) throws DAOSchoolTypeErrorRequest;

    /**
     *
     * @param type_name
     * @return SchoolType
     * @throws DAOSchoolTypeErrorRequest
     */
    SchoolType getSchoolTypeByTypeName(String type_name) throws DAOSchoolTypeErrorRequest;

    /**
     *
     * @param id
     * @return 1 if deleted, 0 if not found
     * @throws DAOSchoolTypeErrorRequest
     */
    int removeSchoolTypeById(int id) throws DAOSchoolTypeErrorRequest;

    /**
     *
     * @param type_name
     * @return 1 if deleted, 0 if not found
     * @throws DAOSchoolTypeErrorRequest
     */
    int removeSchoolTypeByTypeName(String type_name) throws DAOSchoolTypeErrorRequest;

    /**
     *
     * @param schoolType
     * @return 1 - if inserted, 0 - if not
     * @throws DAOSchoolTypeErrorRequest
     */
    int insertSchoolType(SchoolType schoolType) throws DAOSchoolTypeErrorRequest;

    /**
     *
     * @param schoolTypes
     * @return numerous of added students
     * @throws DAOSchoolTypeErrorRequest
     */
    int insertSchoolTypes(ArrayList<SchoolType> schoolTypes) throws DAOSchoolTypeErrorRequest;

    /**
     *
     * @param schoolType
     * @return 1 - if updated, 0 - if update failed
     * @throws DAOSchoolTypeErrorRequest
     */
    int updateSchoolType(SchoolType schoolType) throws DAOSchoolTypeErrorRequest;
}
