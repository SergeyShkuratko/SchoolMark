package template.dao;


import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import template.dto.TestQuestion;

import java.sql.*;

@Repository
public class TestQuestionDAOImplementation {
    private static final Logger logger = Logger.getLogger(TestQuestionDAOImplementation.class);
    public static ConnectionPool connectionManager = TomcatConnectionPool.getInstance();

    private final QuestionCriterionDAOImplementation questionCriterionDAOImplementation;

    @Autowired
    public TestQuestionDAOImplementation(QuestionCriterionDAOImplementation questionCriterionDAOImplementation) {
        this.questionCriterionDAOImplementation = questionCriterionDAOImplementation;
    }

    public int createTestQuestionCascade(TestQuestion testQuestion, int variantId) {
        int questionId = 0;
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO questions(template_variant_id, question, answer) " +
                            "VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, variantId);
            preparedStatement.setString(2, testQuestion.getQuestionText());
            preparedStatement.setString(3, testQuestion.getAnswerText());


            if (preparedStatement.executeUpdate() == 1) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                //получаем id только что добавленного вопроса
                questionId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if (questionId != 0) {
            //создаем варианты заданий данного шаблона
            for (String criterion : testQuestion.getCriterians()) {
                questionCriterionDAOImplementation.createQuestionCriterian(criterion, questionId);
            }
        }

        return questionId;
    }
}
