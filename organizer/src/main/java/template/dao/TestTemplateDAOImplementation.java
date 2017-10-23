package template.dao;

import classes.Question;
import classes.Subject;
import connectionmanager.ConnectionManagerPostgresImpl;
import template.dto.Test;
import template.dto.TestQuestion;
import template.dto.TestTemplate;
import template.dto.TestVariant;

import javax.xml.transform.Templates;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nkm on 15.10.2017.
 */
public class TestTemplateDAOImplementation {
    public static ConnectionManagerPostgresImpl connectionManager = ConnectionManagerPostgresImpl.getInstance();

    public static TestTemplate getTemplateByIdCascade(int templateId) {
        TestTemplate testTemplate = null;
        try {
            PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(
                    "SELECT  *, name AS subject_name, test_templates.id as test_templates_id\n" +
                            "FROM question_verification_criterions  RIGHT JOIN questions\n" +
                            "    ON question_verification_criterions.question_id = questions.id\n" +
                            "  RIGHT JOIN template_variants\n" +
                            "    ON questions.template_variant_id = template_variants.id\n" +
                            "  RIGHT JOIN test_templates\n" +
                            "    ON template_variants.template_id = test_templates.id\n" +
                            "  RIGHT JOIN subjects\n" +
                            "    ON test_templates.subject_id = subjects.id\n" +
                            "WHERE test_templates.id = ?\n" +
                            "ORDER BY variant, question, criterion");

            preparedStatement.setInt(1, templateId);
            ResultSet resultSet = preparedStatement.executeQuery();


            //Создаем мапы вопросов, критериев и вариантов данного темплейта
            Map<Integer, TestQuestion> questionsMap = new HashMap<>();
            Map<Integer, TestVariant> variantsMap = new HashMap<>();
            Subject subject = null;

            while (resultSet.next()) {

                //из запроса один раз создаем предмет
                if (subject == null) {
                    subject = new Subject(resultSet.getInt("subject_id"),
                            resultSet.getString("subject_name"));
                }

                //один раз создаем шаблон
                if (testTemplate == null) {
                    testTemplate = new TestTemplate(
                            resultSet.getInt("test_templates_id"),
                            resultSet.getString("description"),
                            subject,
                            resultSet.getString("topic"),
                            resultSet.getString("difficulty"),
                            resultSet.getInt("class_number"),
                            resultSet.getDate("creation_date").toLocalDate());
                }


                TestVariant testVariant;
                int testVariantId = resultSet.getInt("template_variant_id");
                String testVariantName = resultSet.getString("variant");
                if(testVariantName == null){
                    continue;
                }
                if (variantsMap.containsKey(testVariantId)) {
                    testVariant = variantsMap.get(testVariantId);
                } else {
                    testVariant = new TestVariant(testVariantId,
                            testVariantName);
                    variantsMap.put(testVariantId, testVariant);
                    //если вариант новый - добавляем его в шаблон
                    testTemplate.getTestVariants().add(testVariant);
                }

                TestQuestion testQuestion;
                int questionId = resultSet.getInt("question_id");
                String questionText = resultSet.getString("question");
                String questionAnswerText = resultSet.getString("answer");
                if (questionText == null || questionAnswerText == null){
                    continue;
                }
                if (questionsMap.containsKey(questionId)) {
                    testQuestion = questionsMap.get(questionId);
                } else {
                    testQuestion = new TestQuestion(questionId,
                            questionText,
                            questionAnswerText);
                    questionsMap.put(questionId, testQuestion);
                    //если вопрос новый - добавляем его к варианту
                    testVariant.getTestQuestions().add(testQuestion);
                }

                //каждая новая строчка - это критерий
                String criterionText = resultSet.getString("criterion");
                if (criterionText == null){
                    continue;
                }
                testQuestion.getCriterians().add(criterionText);

            }

            return testTemplate;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testTemplate;
    }

    //TODO передавать учителя, который сейчас в сессии (пока возвращаются вообще все шаблоны)
    public static List<TestTemplate> getAllTemplatesByTeacher() {
        List<TestTemplate> templates = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(
                    "SELECT *, subjects.name as subject_name\n" +
                            "FROM test_templates JOIN subjects\n" +
                            "ON test_templates.subject_id = subjects.id\n" +
                            "WHERE test_templates.status ISNULL\n" +
                            "ORDER BY creation_date;");

            //preparedStatement.setInt(1, ...);

            //Создаем мапу предметов данного темплейта
            Map<Integer, Subject> subjectsMap = new HashMap<>();

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Subject subject;
                if (subjectsMap.containsKey(resultSet.getInt("subject_id"))) {
                    subject = subjectsMap.get(resultSet.getInt("subject_id"));
                } else {
                    subject = new Subject(resultSet.getInt("subject_id"),
                            resultSet.getString("subject_name"));
                    subjectsMap.put(resultSet.getInt("subject_id"), subject);
                }

                templates.add(new TestTemplate(
                        resultSet.getInt("id"),
                        resultSet.getString("description"),
                        subject,
                        resultSet.getString("topic"),
                        resultSet.getString("difficulty"),
                        resultSet.getInt("class_number"),
                        resultSet.getDate("creation_date").toLocalDate()));

                //return templates;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return templates;
    }


    //Cascade означает, что мы также создаем критерии, вопросы и варианты данного шаблона
    public static int createTestTemplateCascade(TestTemplate testTemplate) {
        try {
            PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(
                    "INSERT INTO test_templates(topic, description, class_number, subject_id, difficulty, creation_date) " +
                            "VALUES (?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, testTemplate.getTopic());
            preparedStatement.setString(2, "описание"); //TODO поднять вопрос о целесообразности поля
            preparedStatement.setInt(3, testTemplate.getClassNum());
            preparedStatement.setInt(4, SubjectDAOImplementation.getSubjectId(testTemplate.getSubject()));
            preparedStatement.setString(5, testTemplate.getDifficulty());
            preparedStatement.setDate(6, Date.valueOf(testTemplate.getCreationDate()));


            if (preparedStatement.executeUpdate() == 1) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
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

    public static boolean setStatusDisabled(TestTemplate oldTemplate) {
        try {
            PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(
                    "UPDATE test_templates SET status = 'disabled' where id = ?;");
            preparedStatement.setInt(1, oldTemplate.getId());

            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
