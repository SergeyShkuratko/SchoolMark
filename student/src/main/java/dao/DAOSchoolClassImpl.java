package dao;

import classes.SchoolClass;
import exception.DAOSchoolClassErrorRequestException;

import java.util.ArrayList;

public class DAOSchoolClassImpl implements DAOSchoolClass {
    @Override
    public SchoolClass getSchoolClassById(int id) throws DAOSchoolClassErrorRequestException {
        return null;
    }

    @Override
    public ArrayList<SchoolClass> getSchoolClassesByNumber(int number) throws DAOSchoolClassErrorRequestException {
        return null;
    }

    @Override
    public ArrayList<SchoolClass> getSchoolClassesByName(String name) throws DAOSchoolClassErrorRequestException {
        return null;
    }

    @Override
    public ArrayList<SchoolClass> getSchoolClassesBySchoolId(int school_id) throws DAOSchoolClassErrorRequestException {
        return null;
    }

    @Override
    public int removeSchoolClassById(int id) throws DAOSchoolClassErrorRequestException {
        return 0;
    }

    @Override
    public int removeSchoolClassesByName(String name) throws DAOSchoolClassErrorRequestException {
        return 0;
    }

    @Override
    public int removeSchoolClassesBySchoolId(int school_id) throws DAOSchoolClassErrorRequestException {
        return 0;
    }

    @Override
    public int insertSchoolClass(SchoolClass schoolClass) throws DAOSchoolClassErrorRequestException {
        return 0;
    }

    @Override
    public int insertSchoolClasses(ArrayList<SchoolClass> schoolClasses) throws DAOSchoolClassErrorRequestException {
        return 0;
    }

    @Override
    public int updateSchoolClass(SchoolClass schoolClass) throws DAOSchoolClassErrorRequestException {
        return 0;
    }
}
