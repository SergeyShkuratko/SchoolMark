package template.dao;

import classes.Subject;
import classes.Teacher;
import classes.User;
import connectionmanager.ConnectionManagerPostgresImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nkm on 20.10.2017.
 */
public class TeacherDAOImplementation {
    public static ConnectionManagerPostgresImpl connectionManager = ConnectionManagerPostgresImpl.getInstance();

    public static int getTeacherIdByUser(User user) {
        Teacher teacher = null;

        try {
            PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(
                    "SELECT * FROM teachers WHERE user_id = ?");
            preparedStatement.setInt(1, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return  resultSet.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
