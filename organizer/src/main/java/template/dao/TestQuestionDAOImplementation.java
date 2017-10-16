package template.dao;

import connectionmanager.ConnectionManagerPostgresImpl;
import template.dto.TestQuestion;
import template.dto.TestVariant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nkm on 15.10.2017.
 */
public class TestQuestionDAOImplementation {
    public static ConnectionManagerPostgresImpl connectionManager = ConnectionManagerPostgresImpl.getInstance();

    public static int createTestQuestion(TestQuestion testQuestion, int variantId) {
        try {
            PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(
                    "INSERT INTO questions(template_variant_id, question, answer) " +
                            "VALUES (?,?,?)");
            preparedStatement.setInt(1, variantId);
            preparedStatement.setString(2, testQuestion.getQuestionText());
            preparedStatement.setString(3, testQuestion.getAnswerText());


            if (preparedStatement.executeUpdate() == 1){
                PreparedStatement preparedGetStatement = connectionManager.getConnection().prepareStatement(
                        "SELECT * FROM questions WHERE template_variant_id = ? AND question = ?");
                preparedGetStatement.setInt(1, variantId);
                preparedGetStatement.setString(1, testQuestion.getQuestionText());

                ResultSet resultSet = preparedGetStatement.executeQuery();
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
