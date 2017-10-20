package template.dao;

import connectionmanager.ConnectionManagerPostgresImpl;
import template.dto.Test;

import java.sql.*;

/**
 * Created by nkm on 15.10.2017.
 */
public class TestDAOImplementation {
    public static ConnectionManagerPostgresImpl connectionManager = ConnectionManagerPostgresImpl.getInstance();

    public static int createTest(Test test, int ownerId) {
        int testTemplateId = test.getTestTemplate().getId();
        if(testTemplateId == 0){
            testTemplateId = TestTemplateDAOImplementation.createTestTemplateCascade(test.getTestTemplate());
        }

        try {
            PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(
                    "INSERT INTO tests(test_template_id, owner_id, school_class_id, start_date_time, verification_deadline, status, description) " +
                            "VALUES (?,?,?,?,?,?::test_status, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, testTemplateId);
            preparedStatement.setInt(2, ownerId);
            preparedStatement.setInt(3, test.getSchoolClass().getId());
            preparedStatement.setDate(4, java.sql.Date.valueOf(test.getTestDate()));
            preparedStatement.setDate(5, java.sql.Date.valueOf(test.getTestDate()));
            preparedStatement.setString(6, "new");
            preparedStatement.setString(7, test.getTestDescription());

            if (preparedStatement.executeUpdate() == 1){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                //получаем и возвращаем id только что добавленного теста
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
