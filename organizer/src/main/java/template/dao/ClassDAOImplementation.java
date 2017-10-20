package template.dao;

import classes.SchoolClass;
import classes.Subject;
import connectionmanager.ConnectionManagerPostgresImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nkm on 20.10.2017.
 */
public class ClassDAOImplementation {
    public static ConnectionManagerPostgresImpl connectionManager = ConnectionManagerPostgresImpl.getInstance();

    public static SchoolClass getClassId(int number, String letter, int schoolId) {
        SchoolClass schoolClass = null;
        try {
            PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(
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
            e.printStackTrace();
        }

        return schoolClass;
    }
}
