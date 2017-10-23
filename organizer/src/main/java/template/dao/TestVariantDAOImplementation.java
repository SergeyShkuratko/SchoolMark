package template.dao;

import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import template.dto.TestQuestion;
import template.dto.TestVariant;

import java.sql.*;

/**
 * Created by nkm on 15.10.2017.
 */
public class TestVariantDAOImplementation {
    public static ConnectionPool connectionManager = TomcatConnectionPool.getInstance();

    public static int createTestVariant(TestVariant testVariant, int templateId) {
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
                int variantId = resultSet.getInt(1);
                //создаем варианты данного шаблона
                for (TestQuestion testQuestion : testVariant.getTestQuestions()) {
                    TestQuestionDAOImplementation.createTestQuestion(testQuestion, variantId);
                }
                return variantId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
