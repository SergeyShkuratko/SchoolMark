package template.dao;

import connectionmanager.ConnectionManagerPostgresImpl;
import template.dto.Test;

import java.sql.*;

/**
 * Created by nkm on 15.10.2017.
 */
public class TestDAOImplementation {
    public static ConnectionManagerPostgresImpl connectionManager = ConnectionManagerPostgresImpl.getInstance();

    public static int createTest(Test test) {
        int testTemplateId = test.getTestTemplate().getId();
        if(testTemplateId == 0){
            testTemplateId = TestTemplateDAOImplementation.createTestTemplateCascade(test.getTestTemplate());
        }
//        try {
//            PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(
//                    "INSERT INTO tests(test_template_id, owner_id, school_class_id, start_date_time, verification_deadline, status) " +
//                            "VALUES (?,?,?,?,?,?)",
//                    Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setInt(1, testTemplateId);
//            preparedStatement.setNull(2, 0); //TODO owner_id надо получать
//            preparedStatement.setNull(3, 0); //TODO school_class_id надо получать
//            preparedStatement.setDate(4, java.sql.Date.valueOf(test.getTestDate()));
//            preparedStatement.setDate(5, java.sql.Date.valueOf(test.getTestDate()));
//            preparedStatement.setString(6, "new"); //TODO узнать про работу с enum
//
//
//            if (preparedStatement.executeUpdate() == 1){
//                ResultSet resultSet = preparedStatement.getGeneratedKeys();
//                resultSet.next();
//                //получаем и возвращаем id только что добавленного критерия
//                return resultSet.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return 0;
    }
}
