package template.dao;

import classes.Subject;
import connectionmanager.ConnectionManagerPostgresImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nkm on 16.10.2017.
 */
public class SubjectDAOImplementation {

    public static ConnectionManagerPostgresImpl connectionManager = ConnectionManagerPostgresImpl.getInstance();

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

    public static List<Subject> getAllSubjects() {

        List<Subject> subjects = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(
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
            e.printStackTrace();
        }

        return subjects;
    }
}
