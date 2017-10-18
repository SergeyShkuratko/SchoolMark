package dao;

import classes.SchoolClass;
import classes.Student;
import connectionmanager.ConnectionManagerPostgresImpl;
import exception.DAOSchoolClassErrorRequestException;
import exception.DAOSchoolErrorRequestException;
import exception.DAOStudentErrorRequestException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOSchoolClassImpl implements DAOSchoolClass {

    private final Logger logger = Logger.getLogger(DAOStudent.class);
    private final ConnectionManagerPostgresImpl connectionManagerPostgres
            = ConnectionManagerPostgresImpl.getInstance();
    private final Connection connection;
    private final String baseGetSql = "" +
            "SELECT * FROM school_classes schl_cls ";
    private final String baseRemoveSql = "DELETE FROM school_classes ";
    private final String insertSql = "INSERT INTO school_classes " +
            "(id, number, name, school_id) VALUES " +
            "(?, ?, ?, ?)";
    private final String updateSql = "UPDATE school_classes " +
            "SET number = ?, name = ?, school_id = ? " +
            "WHERE id = ?";

    public DAOSchoolClassImpl() throws SQLException, DAOSchoolClassErrorRequestException {
        if(connectionManagerPostgres == null)
            throw new DAOSchoolClassErrorRequestException("public DAOSchoolClassImpl(): connectionManagerPostgres is null !!!");
        connection = connectionManagerPostgres.getConnection();
    }

    private SchoolClass getOne(String where) throws DAOSchoolClassErrorRequestException {
        try (ResultSet rs = DAOUtils.getResultSetExecuteQueryByWhere(
                connection, baseGetSql + where)) {
            if (rs.next()) {
                return DAOUtils.getSchoolClassByResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DAOSchoolClassErrorRequestException(e.getMessage());
        }
    }

    private ArrayList<SchoolClass> getMany(String where)
            throws DAOSchoolClassErrorRequestException {
        try (ResultSet rs = DAOUtils.getResultSetExecuteQueryByWhere(
                connection, baseGetSql + where)) {
            ArrayList<SchoolClass> schoolClasses = new ArrayList<>();
            while (rs.next()) {
                schoolClasses.add(DAOUtils.getSchoolClassByResultSet(rs));
            }
            return schoolClasses.size() != 0 ? schoolClasses : null;
        } catch (SQLException e) {
            throw new DAOSchoolClassErrorRequestException(e.getMessage());
        }
    }

    @Override
    public SchoolClass getSchoolClassById(int id)
            throws DAOSchoolClassErrorRequestException {
        return this.getOne("WHERE schl_cls.id = " + id);
    }

    @Override
    public ArrayList<SchoolClass> getSchoolClassesByNumber(int number)
            throws DAOSchoolClassErrorRequestException {
        return this.getMany("WHERE schl_cls.number = " + number);
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
