package dao;

import classes.SchoolClass;
import connectionmanager.ConnectionManagerPostgresImpl;
import exception.DAOSchoolClassErrorRequestException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static core.dao.constants.DAOConstants.NULL_POINTER_DB;

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
    public ArrayList<SchoolClass> getSchoolClassesByName(String name)
            throws DAOSchoolClassErrorRequestException {
        return this.getMany("WHERE schl_cls.number = " + name);
    }

    @Override
    public ArrayList<SchoolClass> getSchoolClassesBySchoolId(int school_id)
            throws DAOSchoolClassErrorRequestException {
        return this.getMany("WHERE schl_cls.school_id = " + school_id);
    }

    private int removeSchoolClassesCommon(String where)
            throws DAOSchoolClassErrorRequestException {
        try {
            return DAOUtils.getResultSetExecuteUpdateByWhere(
                    connection, this.baseRemoveSql + where);
        } catch (SQLException e) {
            throw new DAOSchoolClassErrorRequestException(e.getMessage());
        }
    }

    @Override
    public int removeSchoolClassById(int id)
            throws DAOSchoolClassErrorRequestException {
        return this.removeSchoolClassesCommon("WHERE id = " + id);
    }

    @Override
    public int removeSchoolClassesByName(String name)
            throws DAOSchoolClassErrorRequestException {
        return this.removeSchoolClassesCommon("WHERE name = " + name);
    }

    @Override
    public int removeSchoolClassesByNumber(int number)
            throws DAOSchoolClassErrorRequestException {
        return this.removeSchoolClassesCommon("WHERE number = " + number);
    }

    @Override
    public int removeSchoolClassesBySchoolId(int school_id)
            throws DAOSchoolClassErrorRequestException {
        return this.removeSchoolClassesCommon("WHERE school_id = " + school_id);
    }

    private void insertOne(SchoolClass schoolClass, PreparedStatement preparedStatement)
            throws DAOSchoolClassErrorRequestException {
        try {
            preparedStatement.setInt(1, schoolClass.getId());
            preparedStatement.setInt(2, schoolClass.getNum());
            preparedStatement.setString(3, schoolClass.getName());
            preparedStatement.setInt(4, schoolClass.getSchool().getId());
            preparedStatement.addBatch();
        } catch (SQLException e) {
            throw new DAOSchoolClassErrorRequestException(e.getMessage());
        } catch (NullPointerException npe) {
            throw new DAOSchoolClassErrorRequestException(npe.getMessage());
        }
    }

    private void checkInDBaseAndException(SchoolClass schoolClass)
            throws DAOSchoolClassErrorRequestException {
        if(schoolClass.getId() != NULL_POINTER_DB)
            throw new DAOSchoolClassErrorRequestException (
                    "You can't insert object into db which is already there !!!");
    }

    private void checkDoesNotInDBaseAndException(SchoolClass schoolClass)
            throws DAOSchoolClassErrorRequestException {
        if(schoolClass.getId() == NULL_POINTER_DB)
            throw new DAOSchoolClassErrorRequestException (
                    "You can't update object into db which have not there yet !!!");
    }

    @Override
    public int insertSchoolClass(SchoolClass schoolClass)
            throws DAOSchoolClassErrorRequestException {
        this.checkInDBaseAndException(schoolClass);
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            this.insertOne(schoolClass, preparedStatement);
            return preparedStatement.executeBatch().length;
        } catch (SQLException e) {
            throw new DAOSchoolClassErrorRequestException(e.getMessage());
        }
    }

    @Override
    public int insertSchoolClasses(ArrayList<SchoolClass> schoolClasses)
            throws DAOSchoolClassErrorRequestException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            for(SchoolClass schoolClassool : schoolClasses) {
                this.checkInDBaseAndException(schoolClassool);
                this.insertOne(schoolClassool, preparedStatement);
            }
            return preparedStatement.executeBatch().length;
        } catch (SQLException e) {
            throw new DAOSchoolClassErrorRequestException(e.getMessage());
        }
    }

    @Override
    public int updateSchoolClass(SchoolClass schoolClass)
            throws DAOSchoolClassErrorRequestException {
        this.checkDoesNotInDBaseAndException(schoolClass);
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setInt(1, schoolClass.getNum());
            preparedStatement.setString(2, schoolClass.getName());
            preparedStatement.setInt(3, schoolClass.getSchool().getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOSchoolClassErrorRequestException(e.getMessage());
        }
    }
}
