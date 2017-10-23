package dao;

import classes.Student;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import exception.DAOStudentErrorRequestException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

import static constants.DAOConstants.NULL_POINTER_DB;

public class DAOStudentImpl implements DAOStudent {

    private final Logger logger = Logger.getLogger(DAOStudent.class);
    private final ConnectionPool connectionManagerPostgres
            = TomcatConnectionPool.getInstance();
    private final Connection connection;
    private final String baseGetSql = "" +
            "SELECT * FROM students stds " +
            "JOIN school_classes schl_cls ON (stds.school_class_id = schl_cls.id) " +
            "JOIN schools schls ON (schl_cls.school_id = schls.id) " +
            "JOIN school_types schl_tps ON (schls.school_type_id = schl_tps.id) ";
    private final String baseRemoveSql = "DELETE FROM students ";
    private final String insertSql = "INSERT INTO students " +
            "(id, user_id, last_name, first_name, patronymic, school_class_id) VALUES " +
            "(?, ?, ?, ?, ?, ?)";
    private final String updateSql = "UPDATE students " +
            "SET user_id = ?, last_name = ?, first_name = ?, patronymic = ?, school_class_id = ? " +
            "WHERE id = ?";

    public DAOStudentImpl() throws SQLException, DAOStudentErrorRequestException {
        if(connectionManagerPostgres == null)
            throw new DAOStudentErrorRequestException(
                    "public DAOStudentImpl(): connectionManagerPostgres is null !!!");
        connection = connectionManagerPostgres.getConnection();
    }

    private Student getOne(String where) throws DAOStudentErrorRequestException {
        try (ResultSet rs = DAOUtils.getResultSetExecuteQuery(
                connection, baseGetSql + where)) {
            if (rs.next()) {
                return DAOUtils.getStudentByResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DAOStudentErrorRequestException(e.getMessage());
        }
    }

    private ArrayList<Student> getMany(String where) throws DAOStudentErrorRequestException {
        try (ResultSet rs = DAOUtils.getResultSetExecuteQuery(
                connection, baseGetSql + where)) {
            ArrayList<Student> students = new ArrayList<>();
            while (rs.next()) {
                students.add(DAOUtils.getStudentByResultSet(rs));
            }
            return students.size() != 0 ? students : null;
        } catch (SQLException e) {
            throw new DAOStudentErrorRequestException(e.getMessage());
        }
    }

    @Override
    public Student getStudentById(int id) throws DAOStudentErrorRequestException {
        return this.getOne("WHERE stds.id = " + id);
    }

    @Override
    public Student getStudentByUserId(int user_id) throws DAOStudentErrorRequestException {
        return this.getOne("WHERE stds.user_id = " + user_id);
    }

    @Override
    public ArrayList<Student> getStudentsByLastName(String lastName) throws DAOStudentErrorRequestException {
        return this.getMany("WHERE stds.last_name = " + lastName);
    }

    @Override
    public ArrayList<Student> getStudentsByFirstName(String firstName) throws DAOStudentErrorRequestException {
        return this.getMany("WHERE stds.first_name = " + firstName);
    }

    @Override
    public ArrayList<Student> getStudentsByPatronymic(String patronymic) throws DAOStudentErrorRequestException {
        return this.getMany("WHERE stds.patronymic = " + patronymic);
    }

    @Override
    public ArrayList<Student> getStudentsBySchoolClassId(int schoolClassId) throws DAOStudentErrorRequestException {
        return this.getMany("WHERE stds.school_class_id = " + schoolClassId);
    }

    private int removeStudentsCommon(String where) throws DAOStudentErrorRequestException {
        try {
            return DAOUtils.getResultSetExecuteUpdate(
                    connection, this.baseRemoveSql + where);
        } catch (SQLException e) {
            throw new DAOStudentErrorRequestException(e.getMessage());
        }
    }

    @Override
    public int removeStudentById(int id) throws DAOStudentErrorRequestException {
        return this.removeStudentsCommon("WHERE id = " + id);
    }

    @Override
    public int removeStudentByUserId(int user_id) throws DAOStudentErrorRequestException {
        return this.removeStudentsCommon("WHERE user_id = " + user_id);
    }

    @Override
    public int removeStudentsByLastName(String lastName) throws DAOStudentErrorRequestException {
        return this.removeStudentsCommon("WHERE last_name = " + lastName);
    }

    @Override
    public int removeStudentsByFirstName(String firstName) throws DAOStudentErrorRequestException {
        return this.removeStudentsCommon("WHERE first_name = " + firstName);
    }

    @Override
    public int removeStudentsByPatronymic(String patronymic) throws DAOStudentErrorRequestException {
        return this.removeStudentsCommon("WHERE patronymic = " + patronymic);
    }

    @Override
    public int removeStudentsBySchoolClassId(int schoolClassId) throws DAOStudentErrorRequestException {
        return this.removeStudentsCommon("WHERE school_class_id = " + schoolClassId);
    }

    private void insertOne(Student student, PreparedStatement preparedStatement) throws DAOStudentErrorRequestException {
        try {
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setInt(2, student.getUserId());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setString(4, student.getFirstName());
            preparedStatement.setString(5, student.getPatronymic());
            preparedStatement.setInt(6, student.getSchoolClass().getId());
            preparedStatement.addBatch();
        } catch (SQLException e) {
            throw new DAOStudentErrorRequestException(e.getMessage());
        } catch (NullPointerException npe) {
            throw new DAOStudentErrorRequestException(npe.getMessage());
        }
    }

    private void checkInDBaseAndException(Student student)
            throws DAOStudentErrorRequestException {
        if(student.getId() != NULL_POINTER_DB)
            throw new DAOStudentErrorRequestException (
                    "You can't insert object into db which is already there !!!");
    }

    private void checkDoesNotInDBaseAndException(Student student)
            throws DAOStudentErrorRequestException {
        if(student.getId() == NULL_POINTER_DB)
            throw new DAOStudentErrorRequestException (
                    "You can't update object into db which have not there yet !!!");
    }

    @Override
    public int insertStudent(Student student) throws DAOStudentErrorRequestException {
        this.checkInDBaseAndException(student);
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            this.insertOne(student, preparedStatement);
            return preparedStatement.executeBatch().length;
        } catch (SQLException e) {
            throw new DAOStudentErrorRequestException(e.getMessage());
        }
    }

    @Override
    public int insertStudents(ArrayList<Student> students) throws DAOStudentErrorRequestException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            for(Student student : students) {
                this.checkInDBaseAndException(student);
                this.insertOne(student, preparedStatement);
            }
            return preparedStatement.executeBatch().length;
        } catch (SQLException e) {
            throw new DAOStudentErrorRequestException(e.getMessage());
        }
    }

    @Override
    public int updateStudent(Student student) throws DAOStudentErrorRequestException {
        this.checkDoesNotInDBaseAndException(student);
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setInt(1, student.getUserId());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getFirstName());
            preparedStatement.setString(4, student.getPatronymic());
            preparedStatement.setInt(5, student.getSchoolClass().getId());
            preparedStatement.setInt(6, student.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOStudentErrorRequestException(e.getMessage());
        }
    }

}
