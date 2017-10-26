package student.dao;

import classes.School;
import classes.SchoolClass;
import classes.SchoolType;
import classes.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class DAOUtils {

    public static SchoolClass getSchoolClassByResultSet(ResultSet rs) throws SQLException {
        /**
         * @ надо отработать ситуацию с несуществующим классом !!!
         */
        return new SchoolClass(
                rs.getInt("schl_cls.id"),
                rs.getInt("schl_cls.number"),
                rs.getString("schl_cls.name")
        );
    }

    public static SchoolType getSchoolTypeByResultSet(ResultSet rs) throws SQLException {
        /**
         * @ надо отработать ситуацию с несуществующим типом школы !!!
         */
        return new SchoolType(
                rs.getInt("schl_tps.id"),
                rs.getString("schl_tps.type_name")
        );
    }

    public static School getSchoolByResultSet(ResultSet rs) throws SQLException {
        /**
         * @ надо отработать ситуацию с несуществующей школой !!!
         */
        return new School(
                rs.getInt("schls.id"),
                rs.getString("schls.name"),
                rs.getString("schls.region"),
                rs.getString("schls.city"),
                getSchoolTypeByResultSet(rs)
        );
    }

    public static Student getStudentByResultSet(ResultSet rs) throws SQLException {
        return new Student(
                rs.getInt("id"),
                rs.getInt("user_id"),
                //TODO join on users table
                "login",
                LocalDate.now(),
                rs.getString("last_name"),
                rs.getString("first_name"),
                rs.getString("patronymic"),
                getSchoolClassByResultSet(rs),
                getSchoolByResultSet(rs)
        );
    }

    public static ResultSet getResultSetExecuteQuery(
            Connection connection, String baseSql) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(baseSql);
    }

    public static int getResultSetExecuteUpdate(
            Connection connection, String baseSql) throws SQLException {
        Statement stmt = connection.createStatement();
            return stmt.executeUpdate(baseSql);
    }

}
