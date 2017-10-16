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
public class TestVariantDAOImplementation {
    public static ConnectionManagerPostgresImpl connectionManager = ConnectionManagerPostgresImpl.getInstance();

    public static int createTestVariant(TestVariant testVariant, int templateId) {
        try {
            PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(
                    "INSERT INTO template_variants(variant, template_id) " +
                            "VALUES (?,?,?,?)");
            preparedStatement.setString(1, testVariant.getVariant()); //TODO поднять вопрос о целесообразности поля
            preparedStatement.setInt(2, templateId);

            if (preparedStatement.executeUpdate() == 1){
                PreparedStatement preparedGetStatement = connectionManager.getConnection().prepareStatement(
                        "SELECT * FROM template_variants WHERE variant = ? and template_id = ?");
                preparedGetStatement.setString(1, testVariant.getVariant());
                preparedGetStatement.setInt(2, templateId);

                ResultSet resultSet = preparedGetStatement.executeQuery();
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
