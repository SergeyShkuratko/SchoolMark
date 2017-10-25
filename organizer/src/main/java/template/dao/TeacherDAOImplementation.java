package template.dao;

import classes.User;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import org.apache.log4j.Logger;
import template.dto.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nkm on 20.10.2017.
 */
public class TeacherDAOImplementation {
    private static final Logger logger = Logger.getLogger(TeacherDAOImplementation.class);
    public static ConnectionPool connectionManager = TomcatConnectionPool.getInstance();

    public static Teacher getTeacherByUser(User user) {
        Teacher teacher = null;

        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM teachers WHERE user_id = ?");
            preparedStatement.setInt(1, user.getUserId());

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            teacher = new Teacher(
                    resultSet.getInt("id"),
                    resultSet.getInt("user_id"),
                    resultSet.getString("last_name"),
                    resultSet.getString("first_name"),
                    resultSet.getString("patronymic"),
                    resultSet.getInt("school_id"),
                    resultSet.getInt("min_class_number"),
                    resultSet.getInt("max_class_number"));

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return teacher;
    }

    public static Teacher getTeacherByUserId(int userId) {
        Teacher teacher = null;

        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM teachers WHERE user_id = ?");
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            teacher = new Teacher(
                    resultSet.getInt("id"),
                    resultSet.getInt("user_id"),
                    resultSet.getString("last_name"),
                    resultSet.getString("first_name"),
                    resultSet.getString("patronymic"),
                    resultSet.getInt("school_id"),
                    resultSet.getInt("min_class_number"),
                    resultSet.getInt("max_class_number"));

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return teacher;
    }
}
