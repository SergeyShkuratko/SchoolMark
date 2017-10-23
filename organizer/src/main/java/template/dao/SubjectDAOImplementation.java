package template.dao;

import classes.Subject;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nkm on 16.10.2017.
 */
public class SubjectDAOImplementation {
    public static ConnectionPool connectionManager = TomcatConnectionPool.getInstance();

    public static int getSubjectId(Subject subject) {
        Subject tmpSubject = null;
        try {
            PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(
                    "SELECT * FROM subjects WHERE name = ?");
            preparedStatement.setString(1, subject.getName());

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            tmpSubject = new Subject(
                    resultSet.getInt("id"),
                    resultSet.getString("name"));
            return tmpSubject.getId();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
