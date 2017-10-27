package template.dao;

import classes.Subject;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAOImplementation {
    private static final Logger logger = Logger.getLogger(SubjectDAOImplementation.class);
    public static ConnectionPool connectionManager = TomcatConnectionPool.getInstance();

    public static int getSubjectId(Subject subject) {
        int subjectId = 0;
//        Subject tmpSubject = null;
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM subjects WHERE name = ?");
            preparedStatement.setString(1, subject.getName());

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
//            tmpSubject = new Subject(
//                    resultSet.getInt("id"),
//                    resultSet.getString("name"));
             subjectId = resultSet.getInt("id");
             return subjectId;

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return subjectId;
    }

    public static List<Subject> getAllSubjects() {

        List<Subject> subjects = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM subjects");
//            preparedStatement.setString(1, subject.getName());

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Subject tmpSubject = new Subject(
                        resultSet.getInt("id"),
                        resultSet.getString("name"));
                subjects.add(tmpSubject);
            }

            return subjects;

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return subjects;
    }
}
