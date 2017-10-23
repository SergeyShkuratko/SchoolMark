package template.dao;


import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import template.dto.TestQuestion;
import template.dto.TestVariant;

import java.sql.*;

/**
 * Created by nkm on 15.10.2017.
 */
public class TestQuestionDAOImplementation {
    public static ConnectionPool connectionManager = TomcatConnectionPool.getInstance();

    public static int createTestQuestion(TestQuestion testQuestion, int variantId) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO questions(template_variant_id, question, answer) " +
                            "VALUES (?,?,?)",
                                Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, variantId);
            preparedStatement.setString(2, testQuestion.getQuestionText());
            preparedStatement.setString(3, testQuestion.getAnswerText());


            if (preparedStatement.executeUpdate() == 1){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                //получаем id только что добавленного вопроса
                int questionId = resultSet.getInt(1);
                //создаем варианты заданий данного шаблона
                for (String criterion : testQuestion.getCriterians()) {
                    QuestionCriterionDAOImplementation.createQuestionCriterian(criterion, questionId);
                }
                return questionId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
