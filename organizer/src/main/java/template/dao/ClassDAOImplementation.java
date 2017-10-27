package template.dao;

import classes.SchoolClass;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import org.apache.log4j.Logger;
import template.dto.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassDAOImplementation {
    private static final Logger logger = Logger.getLogger(ClassDAOImplementation.class);
    private static ConnectionPool connectionManager = TomcatConnectionPool.getInstance();

    public static List<Integer> getClassNumbersByTeacher(Teacher teacher){
        List<Integer> schoolClassNumbers = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT DISTINCT(number) FROM school_classes\n" +
                            "WHERE school_id = ?" +
                            " AND number BETWEEN ? AND ?;");
            preparedStatement.setInt(1, teacher.getSchoolId());
            preparedStatement.setInt(2, teacher.getMinClassNumber());
            preparedStatement.setInt(3, teacher.getMaxClassNumber());

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                schoolClassNumbers.add(resultSet.getInt("number"));
            }
            return schoolClassNumbers;

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return schoolClassNumbers;
    }

    public static List<String> getClassNamesByTeacher(Teacher teacher){
        List<String> schoolClassNames = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT DISTINCT(name) FROM school_classes\n" +
                            "WHERE school_id = ?" +
                            " AND number BETWEEN ? AND ?;");
            preparedStatement.setInt(1, teacher.getSchoolId());
            preparedStatement.setInt(2, teacher.getMinClassNumber());
            preparedStatement.setInt(3, teacher.getMaxClassNumber());

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                schoolClassNames.add(resultSet.getString("name"));
            }
            return schoolClassNames;

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return schoolClassNames;
    }


    public static SchoolClass getClassByNumAndName(int number, String letter, int schoolId) {
        SchoolClass schoolClass = null;
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM school_classes WHERE" +
                            " number = ? AND name = ? AND school_id = ?");
            preparedStatement.setInt(1, number);
            preparedStatement.setString(2, letter);
            preparedStatement.setInt(3, schoolId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            schoolClass = new SchoolClass(
                    resultSet.getInt("id"),
                    resultSet.getInt("number"),
                    resultSet.getString("name"));
            return schoolClass;

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return schoolClass;
    }
}
