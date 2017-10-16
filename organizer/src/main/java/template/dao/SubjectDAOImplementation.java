package template.dao;

import classes.Subject;
import connectionmanager.ConnectionManagerPostgresImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nkm on 16.10.2017.
 */
public class SubjectDAOImplementation {
    public static ConnectionManagerPostgresImpl connectionManager = ConnectionManagerPostgresImpl.getInstance();

    public static int getSubjectId(Subject subject) {
        try {
            PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(
                    "SELECT * FROM subjects WHERE name = ?");
            preparedStatement.setString(1, subject.getName());

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Subject tmpSubject = new Subject(
                    resultSet.getInt("id"),
                    resultSet.getString("name"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subject.getId();
    }

}
