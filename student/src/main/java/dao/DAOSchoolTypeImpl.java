package dao;

import classes.SchoolClass;
import classes.SchoolType;
import classes.Student;
import connectionmanager.ConnectionManagerPostgresImpl;
import exception.DAOSchoolClassErrorRequestException;
import exception.DAOSchoolErrorRequestException;
import exception.DAOSchoolTypeErrorRequest;
import exception.DAOStudentErrorRequestException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static constants.DAOConstants.NULL_POINTER_DB;

public class DAOSchoolTypeImpl implements DAOSchoolType {


    private final Logger logger = Logger.getLogger(DAOStudent.class);
    private final ConnectionManagerPostgresImpl connectionManagerPostgres
            = ConnectionManagerPostgresImpl.getInstance();
    private final Connection connection;
    private final String baseGetSql = "SELECT * FROM school_types schl_tps ";
    private final String baseRemoveSql = "DELETE FROM school_types ";
    private final String insertSql = "INSERT INTO school_types (type_name) VALUES (?)";
    private final String updateSql = "UPDATE students SET type_name = ? WHERE id = ?";

    public DAOSchoolTypeImpl() throws SQLException, DAOSchoolTypeErrorRequest {
        if(connectionManagerPostgres == null)
            throw new DAOSchoolTypeErrorRequest(
                    "public DAOSchoolTypeImpl(): connectionManagerPostgres is null !!!");
        connection = connectionManagerPostgres.getConnection();
    }

    private SchoolType getOne(String where) throws DAOSchoolTypeErrorRequest {
        try (ResultSet rs = DAOUtils.getResultSetExecuteQuery(
                connection, baseGetSql + where)) {
            if (rs.next()) {
                return DAOUtils.getSchoolTypeByResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DAOSchoolTypeErrorRequest(e.getMessage());
        }
    }

    @Override
    public SchoolType getSchoolTypeById(int id) throws DAOSchoolTypeErrorRequest {
        return this.getOne("WHERE schl_tps.id = " + id);
    }

    @Override
    public SchoolType getSchoolTypeByTypeName(String type_name) throws DAOSchoolTypeErrorRequest {
        return this.getOne("WHERE schl_tps.type_name = " + type_name);
    }

    private int removeSchoolTypeCommon(String where)
            throws DAOSchoolTypeErrorRequest {
        try {
            return DAOUtils.getResultSetExecuteUpdate(
                    connection, this.baseRemoveSql + where);
        } catch (SQLException e) {
            throw new DAOSchoolTypeErrorRequest(e.getMessage());
        }
    }

    @Override
    public int removeSchoolTypeById(int id) throws DAOSchoolTypeErrorRequest {
        return this.removeSchoolTypeCommon("WHERE id = " + id);
    }

    @Override
    public int removeSchoolTypeByTypeName(String type_name) throws DAOSchoolTypeErrorRequest {
        return this.removeSchoolTypeCommon("WHERE type_name = " + type_name);
    }

    private void insertOne(SchoolType schoolType, PreparedStatement preparedStatement)
            throws DAOSchoolTypeErrorRequest {
        try {
            preparedStatement.setString(1, schoolType.getTypeName());
            preparedStatement.addBatch();
        } catch (SQLException e) {
            throw new DAOSchoolTypeErrorRequest(e.getMessage());
        } catch (NullPointerException npe) {
            throw new DAOSchoolTypeErrorRequest(npe.getMessage());
        }
    }

    private void checkInDBaseAndException(SchoolType schoolType)
            throws DAOSchoolTypeErrorRequest {
        if(schoolType.getId() != NULL_POINTER_DB)
            throw new DAOSchoolTypeErrorRequest (
                    "You can't insert object into db which is already there !!!");
    }

    private void checkDoesNotInDBaseAndException(SchoolType schoolType)
            throws DAOSchoolTypeErrorRequest {
        if(schoolType.getId() == NULL_POINTER_DB)
            throw new DAOSchoolTypeErrorRequest (
                    "You can't update object into db which have not there yet !!!");
    }

    @Override
    public int insertSchoolType(SchoolType schoolType) throws DAOSchoolTypeErrorRequest {
        this.checkInDBaseAndException(schoolType);
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            this.insertOne(schoolType, preparedStatement);
            return preparedStatement.executeBatch().length;
        } catch (SQLException e) {
            throw new DAOSchoolTypeErrorRequest(e.getMessage());
        }
    }

    @Override
    public int insertSchoolTypes(ArrayList<SchoolType> schoolTypes) throws DAOSchoolTypeErrorRequest {
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            for(SchoolType schoolType : schoolTypes) {
                this.checkInDBaseAndException(schoolType);
                this.insertOne(schoolType, preparedStatement);
            }
            return preparedStatement.executeBatch().length;
        } catch (SQLException e) {
            throw new DAOSchoolTypeErrorRequest(e.getMessage());
        }
    }

    @Override
    public int updateSchoolType(SchoolType schoolType) throws DAOSchoolTypeErrorRequest {
        this.checkDoesNotInDBaseAndException(schoolType);
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setString(1, schoolType.getTypeName());
            preparedStatement.setInt(2, schoolType.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOSchoolTypeErrorRequest(e.getMessage());
        }
    }
}