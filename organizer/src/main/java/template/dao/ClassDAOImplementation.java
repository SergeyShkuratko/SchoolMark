package template.dao;

import classes.SchoolClass;
import connectionmanager.ConnectionManagerPostgresImpl;
import template.dto.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nkm on 20.10.2017.
 */
public class ClassDAOImplementation {
    public static ConnectionManagerPostgresImpl connectionManager = ConnectionManagerPostgresImpl.getInstance();


    public static List<Integer> getClassNumbersByTeacher(Teacher teacher){
        List<Integer> schoolClassNumbers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(
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
            e.printStackTrace();
        }

        return schoolClassNumbers;
    }

    public static List<String> getClassNamesByTeacher(Teacher teacher){
        List<String> schoolClassNames = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(
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
            e.printStackTrace();
        }

        return schoolClassNames;
    }


    public static SchoolClass getClassByNumAndName(int number, String letter, int schoolId) {
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
