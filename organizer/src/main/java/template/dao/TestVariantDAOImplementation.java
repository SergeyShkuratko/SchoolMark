package template.dao;

import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import org.apache.log4j.Logger;
import template.dto.TestQuestion;
import template.dto.TestVariant;

import java.sql.*;

public class TestVariantDAOImplementation {
    private static final Logger logger = Logger.getLogger(TestVariantDAOImplementation.class);
    public static ConnectionPool connectionManager = TomcatConnectionPool.getInstance();

    public static int createTestVariantCascade(TestVariant testVariant, int templateId) {
        int variantId = 0;
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO template_variants(variant, template_id) " +
                            "VALUES (?,?)",
                                Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, testVariant.getVariant()); //TODO поднять вопрос о целесообразности поля
            preparedStatement.setInt(2, templateId);

            if (preparedStatement.executeUpdate() == 1){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                //возвращаем id только что добавленного вопроса
                variantId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(variantId != 0){
            //создаем варианты данного шаблона
            for (TestQuestion testQuestion : testVariant.getTestQuestions()) {
                TestQuestionDAOImplementation.createTestQuestionCascade(testQuestion, variantId);
            }
        }

        return variantId;
    }
}
