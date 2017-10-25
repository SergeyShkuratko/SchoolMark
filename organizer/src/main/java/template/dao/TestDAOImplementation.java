package template.dao;

import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import org.apache.log4j.Logger;
import template.dto.Teacher;
import template.dto.Test;

import java.sql.*;

/**
 * Created by nkm on 15.10.2017.
 */
public class TestDAOImplementation {
    private static final Logger logger = Logger.getLogger(TestDAOImplementation.class);
    public static ConnectionPool connectionManager = TomcatConnectionPool.getInstance();

    public static int createTest(Test test, Teacher teacher) {
        int testTemplateId = test.getTestTemplate().getId();
        if(testTemplateId == 0){ //значит шаблон не загружали, значит его нет в БД, тогда создаем
            testTemplateId = TestTemplateDAOImplementation.createTestTemplateCascade(test.getTestTemplate());
        }

        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO tests(test_template_id, owner_id, school_class_id, start_date_time, verification_deadline, status, description) " +
                            "VALUES (?,?,?,?,?,?::test_status, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, testTemplateId);
            preparedStatement.setInt(2, teacher.getId());
            preparedStatement.setInt(3, test.getSchoolClass().getId());
            preparedStatement.setDate(4, Date.valueOf(test.getTestDate()));
            preparedStatement.setDate(5, Date.valueOf(test.getTestDate()));
            preparedStatement.setString(6, "new");
            preparedStatement.setString(7, test.getTestDescription());

            if (preparedStatement.executeUpdate() == 1){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                //получаем и возвращаем id только что добавленного теста
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return 0;
    }
}
