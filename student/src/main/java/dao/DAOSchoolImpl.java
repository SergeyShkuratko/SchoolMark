package dao;

import classes.School;
import connectionmanager.ConnectionManagerPostgresImpl;
import exception.DAOSchoolErrorRequestException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

import static constants.DAOConstants.NULL_POINTER_DB;

public class DAOSchoolImpl implements DAOSchool {

    private final Logger logger = Logger.getLogger(DAOSchool.class);
    private final ConnectionManagerPostgresImpl connectionManagerPostgres
            = ConnectionManagerPostgresImpl.getInstance();
    private final Connection connection;
    private final String baseGetSql = "" +
            "SELECT * FROM schools schls " +
            "JOIN school_types schl_tps ON (schls.school_type_id = schl_tps.id) ";
    private final String baseRemoveSql = "DELETE FROM schools ";
    private final String insertSql = "INSERT INTO schools " +
            "(id, name, region, city, school_type_id) VALUES " +
            "(?, ?, ?, ?, ?)";
    private final String updateSql = "UPDATE Schools " +
            "SET name = ?, region = ?, city = ?, school_type_id = ? " +
            "WHERE id = ?";

    public DAOSchoolImpl() throws SQLException, DAOSchoolErrorRequestException {
        if(connectionManagerPostgres == null)
            throw new DAOSchoolErrorRequestException("public DAOSchoolImpl(): connectionManagerPostgres is null !!!");
        connection = connectionManagerPostgres.getConnection();
    }

    private School getOne(String where) throws DAOSchoolErrorRequestException {
        try (ResultSet rs = DAOUtils.getResultSetExecuteQuery(
                connection, baseGetSql + where)) {
            if (rs.next()) {
                return DAOUtils.getSchoolByResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DAOSchoolErrorRequestException(e.getMessage());
        }
    }

    private ArrayList<School> getMany(String where) throws DAOSchoolErrorRequestException {
        try (ResultSet rs = DAOUtils.getResultSetExecuteQuery(
                connection, baseGetSql + where)) {
            ArrayList<School> Schools = new ArrayList<>();
            while (rs.next()) {
                Schools.add(DAOUtils.getSchoolByResultSet(rs));
            }
            return Schools.size() != 0 ? Schools : null;
        } catch (SQLException e) {
            throw new DAOSchoolErrorRequestException(e.getMessage());
        }
    }

    @Override
    public School getSchoolById(int id) throws DAOSchoolErrorRequestException {
        return this.getOne("WHERE schls.id = " + id);
    }

    @Override
    public ArrayList<School> getSchoolsByName(String name) throws DAOSchoolErrorRequestException {
        return this.getMany("WHERE schls.name = " + name);
    }

    @Override
    public ArrayList<School> getSchoolsByRegion(String region) throws DAOSchoolErrorRequestException {
        return this.getMany("WHERE schls.region = " + region);
    }

    @Override
    public ArrayList<School> getSchoolsByCity(String city) throws DAOSchoolErrorRequestException {
        return this.getMany("WHERE schls.city = " + city);
    }

    @Override
    public ArrayList<School> getSchoolsBySchoolTypeId(String school_type_id) throws DAOSchoolErrorRequestException {
        return this.getMany("WHERE schls.school_type_id = " + school_type_id);
    }

    private int removeSchoolsCommon(String where)
            throws DAOSchoolErrorRequestException {
        try {
            return DAOUtils.getResultSetExecuteUpdate(
                    connection, this.baseRemoveSql + where);
        } catch (SQLException e) {
            throw new DAOSchoolErrorRequestException(e.getMessage());
        }
    }

    @Override
    public int removeSchoolById(int id) throws DAOSchoolErrorRequestException {
        return this.removeSchoolsCommon("WHERE id = " + id);
    }

    @Override
    public int removeSchoolByName(String name) throws DAOSchoolErrorRequestException {
        return this.removeSchoolsCommon("WHERE name = " + name);
    }

    @Override
    public int removeSchoolsByRegion(String region) throws DAOSchoolErrorRequestException {
        return this.removeSchoolsCommon("WHERE region = " + region);
    }

    @Override
    public int removeSchoolsByCity(String city) throws DAOSchoolErrorRequestException {
        return this.removeSchoolsCommon("WHERE city = " + city);
    }

    @Override
    public int removeSchoolsBySchoolTypeId(int school_type_id) throws DAOSchoolErrorRequestException {
        return this.removeSchoolsCommon("WHERE school_type_id = " + school_type_id);
    }

    private void insertOne(School School, PreparedStatement preparedStatement) throws DAOSchoolErrorRequestException {
        try {
            preparedStatement.setInt(1, School.getId());
            preparedStatement.setString(2, School.getName());
            preparedStatement.setString(3, School.getRegion());
            preparedStatement.setString(4, School.getCity());
            preparedStatement.setInt(5, School.getSchoolTypeId().getId());
            preparedStatement.addBatch();
        } catch (SQLException e) {
            throw new DAOSchoolErrorRequestException(e.getMessage());
        } catch (NullPointerException npe) {
            throw new DAOSchoolErrorRequestException(npe.getMessage());
        }
    }

    private void checkInDBaseAndException(School School)
            throws DAOSchoolErrorRequestException {
        if(School.getId() != NULL_POINTER_DB)
            throw new DAOSchoolErrorRequestException (
                    "You can't insert object into db which is already there !!!");
    }

    private void checkDoesNotInDBaseAndException(School School)
            throws DAOSchoolErrorRequestException {
        if(School.getId() == NULL_POINTER_DB)
            throw new DAOSchoolErrorRequestException (
                    "You can't update object into db which have not there yet !!!");
    }

    @Override
    public int insertSchool(School School) throws DAOSchoolErrorRequestException {
        this.checkInDBaseAndException(School);
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            this.insertOne(School, preparedStatement);
            return preparedStatement.executeBatch().length;
        } catch (SQLException e) {
            throw new DAOSchoolErrorRequestException(e.getMessage());
        }
    }

    @Override
    public int insertSchools(ArrayList<School> Schools) throws DAOSchoolErrorRequestException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            for(School School : Schools) {
                this.checkInDBaseAndException(School);
                this.insertOne(School, preparedStatement);
            }
            return preparedStatement.executeBatch().length;
        } catch (SQLException e) {
            throw new DAOSchoolErrorRequestException(e.getMessage());
        }
    }

    @Override
    public int updateSchool(School School) throws DAOSchoolErrorRequestException {
        this.checkDoesNotInDBaseAndException(School);
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setString(1, School.getName());
            preparedStatement.setString(2, School.getRegion());
            preparedStatement.setString(3, School.getCity());
            preparedStatement.setInt(4, School.getSchoolTypeId().getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOSchoolErrorRequestException(e.getMessage());
        }
    }

}
