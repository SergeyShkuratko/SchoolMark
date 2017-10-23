package template.dao;

import classes.User;
import connectionmanager.ConnectionManagerPostgresImpl;
import template.dto.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nkm on 20.10.2017.
 */
public class TeacherDAOImplementation {
    public static ConnectionManagerPostgresImpl connectionManager = ConnectionManagerPostgresImpl.getInstance();

    public static Teacher getTeacherByUser(User user) {
        Teacher teacher = null;

        try {
            PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(
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
            return teacher;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
