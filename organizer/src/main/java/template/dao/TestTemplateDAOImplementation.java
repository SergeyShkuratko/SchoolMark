package template.dao;

import classes.Subject;
import connectionmanager.ConnectionManagerPostgresImpl;
import template.dto.TestQuestion;
import template.dto.TestTemplate;
import template.dto.TestVariant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nkm on 15.10.2017.
 */
public class TestTemplateDAOImplementation {
    public static ConnectionManagerPostgresImpl connectionManager = ConnectionManagerPostgresImpl.getInstance();

    //Cascade означает, что мы также создаем критерии, вопросы и варианты данного шаблона
    public static int createTestTemplateCascade(TestTemplate testTemplate) {
        try {
            PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(
                    "INSERT INTO test_templates(topic, description, class_number, subject_id) " +
                            "VALUES (?,?,?,?)");
            preparedStatement.setString(1, ""); //TODO поднять вопрос о целесообразности поля
            preparedStatement.setString(2, testTemplate.getDescription());
            preparedStatement.setInt(3, testTemplate.getClassNum());
            preparedStatement.setInt(4, SubjectDAOImplementation.getSubjectId(testTemplate.getSubject()));


            if (preparedStatement.executeUpdate() == 1){
                PreparedStatement preparedGetStatement = connectionManager.getConnection().prepareStatement(
                        "SELECT * FROM test_templates WHERE description = ?");
                preparedGetStatement.setString(1, testTemplate.getDescription());

                ResultSet resultSet = preparedGetStatement.executeQuery();
                resultSet.next();
                //получаем id только что добавленного темплейта
                int templateId = resultSet.getInt(1);
                //создаем варианты заданий данного шаблона
                for (TestVariant testVariant : testTemplate.getTestVariants()) {
                    TestVariantDAOImplementation.createTestVariant(testVariant, templateId);
                }
                return templateId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
