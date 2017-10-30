package com.inno.db.dao;

import com.inno.db.dto.*;
import com.inno.db.exception.DaoException;
import com.inno.utils.DateConverter;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Repository
public class PgTestStatisticDao implements TestStatisticDao {
    private DateConverter dateConverter;

    @Autowired
    public void setDateConverter(DateConverter dateConverter) {
        this.dateConverter = dateConverter;
    }

    private ConnectionPool connectionManager;
    private final String SQL_TEST_STATISTIC =
            "SELECT t.id AS test_id, t.start_date_time::DATE AS date, " +
                "concat_ws(' ', tr.last_name, tr.first_name, tr.patronymic) AS organizer, " +
                "sbj.name AS subject, cl.name AS class_name, AVG(vr.mark) AS average_mark " +
            "FROM tests t " +
                "JOIN teachers tr ON t.owner_id = tr.id " +
                "JOIN works w ON w.test_id = t.id " +
                "JOIN test_templates tt ON t.test_template_id = tt.id " +
                "JOIN school_classes cl ON t.school_class_id = cl.id " +
                "JOIN verification_results vr ON vr.work_id = w.id " +
                "JOIN subjects sbj ON tt.subject_id = sbj.id " +
            "WHERE tr.school_id = (SELECT school_id " +
                                    "FROM directors " +
                                    "WHERE user_id=?) " +
                "AND (t.start_date_time::date >= ?) " +
                "AND (t.start_date_time::date <= ?) " +
            "GROUP BY t.id, date, organizer, subject, class_name";

    private final String SQL_TEST_AND_WORKS_INFO =
            "SELECT q.id AS question_id, q.question, q.answer, qvc.id AS criterion_id, qvc.criterion, " +
                "w.id AS work_id, concat_ws(' ', st.last_name, st.first_name, st.patronymic) AS student, " +
                "vr.mark, w.status = 'reverification' AS was_appellation, tv.variant, tv.id AS test_variant_id " +
            "FROM tests t " +
                "JOIN works w ON w.test_id = t.id " +
                "JOIN test_templates tt ON t.test_template_id = tt.id " +
                "JOIN template_variants tv ON tv.template_id = tt.id " +
                "JOIN questions q ON q.template_variant_id = tv.id " +
                "JOIN students st ON w.student_id = st.id " +
                "JOIN verification_results vr ON vr.work_id = w.id " +
                "LEFT JOIN question_verification_criterions qvc ON qvc.question_id = q.id " +
            "WHERE t.id = ?";

    public PgTestStatisticDao() {
        this.connectionManager = TomcatConnectionPool.getInstance();
    }

    @Override
    public List<TestStatisticDto> getTestsStatisticByUserId(int userId, LocalDate dateFrom, LocalDate dateTo) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_TEST_STATISTIC)) {
            statement.setInt(1, userId);
            statement.setDate(2, Date.valueOf(dateFrom));
            statement.setDate(3, Date.valueOf(dateTo));

            ResultSet resultSet = statement.executeQuery();

            List<TestStatisticDto> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(
                        new TestStatisticDto(
                                resultSet.getInt("test_id"),
                                dateConverter.formatLocalDateToString(resultSet.getDate("date").toLocalDate()),
                                resultSet.getString("organizer"),
                                resultSet.getString("subject"),
                                resultSet.getString("class_name"),
                                resultSet.getFloat("average_mark")));
            }

            return result;
        } catch (SQLException e) {
            throw new DaoException("Exception occurred while getting test statistic from database", e);
        }
    }

    @Override
    public TestAndWorksInfoDto getTestAndWorksInfo(int testId) {

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_TEST_AND_WORKS_INFO)) {
            statement.setInt(1, testId);
            ResultSet resultSet = statement.executeQuery();

            return convertToTestAndWorksInfoDto(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Exception while getting test and works info", e);
        }
    }

    private TestAndWorksInfoDto convertToTestAndWorksInfoDto(ResultSet resultSet) throws SQLException {
        Set<TestVariantDto> testVariants = new HashSet<>();
        Map<Integer, Set<QuestionDto>> variantIdAndQuestionMap = new HashMap<>();
        Map<Integer, Set<CriterionDto>> questionIdAndCriterionMap = new HashMap<>();
        Set<WorkDto> workList = new HashSet<>();

        while (resultSet.next()) {
            int testVariantId = addTestVariant(testVariants, resultSet);
            int questionId = addQuestion(variantIdAndQuestionMap, testVariantId, resultSet);
            addCriterion(questionIdAndCriterionMap, questionId, resultSet);
            addWork(workList, resultSet);
        }

        for (TestVariantDto testVariant : testVariants) {
            Set<QuestionDto> questions = variantIdAndQuestionMap.get(testVariant.getId());

            for (QuestionDto question : questions) {
                question.addAllCriterion(questionIdAndCriterionMap.get(question.getId()));
            }

            testVariant.addAllQuestions(questions);
        }

        return new TestAndWorksInfoDto(testVariants, workList);
    }

    private void addWork(Set<WorkDto> workList, ResultSet resultSet) throws SQLException {
        WorkDto work = new WorkDto(resultSet.getInt("work_id"),
                resultSet.getString("student"), resultSet.getInt("mark"),
                resultSet.getBoolean("was_appellation"));
        workList.add(work);
    }

    private void addCriterion(Map<Integer, Set<CriterionDto>> questionIdAndCriterionMap, int questionId,
                              ResultSet resultSet) throws SQLException {
        CriterionDto criterion = new CriterionDto(resultSet.getInt("criterion_id"),
                resultSet.getString("criterion"));
        if (questionIdAndCriterionMap.containsKey(questionId)) {
            questionIdAndCriterionMap.get(questionId).add(criterion);
        } else {
            Set<CriterionDto> criterionList = new HashSet<>();
            criterionList.add(criterion);
            questionIdAndCriterionMap.put(questionId, criterionList);
        }
    }

    private int addQuestion(Map<Integer, Set<QuestionDto>> variantIdAndQuestionMap, int testVariantId,
                            ResultSet resultSet) throws SQLException {
        int questionId = resultSet.getInt("question_id");

        QuestionDto question = new QuestionDto(questionId, resultSet.getString("question"),
                resultSet.getString("answer"));
        if (variantIdAndQuestionMap.containsKey(testVariantId)) {
            variantIdAndQuestionMap.get(testVariantId).add(question);
        } else {
            Set<QuestionDto> questions = new HashSet<>();
            questions.add(question);
            variantIdAndQuestionMap.put(testVariantId, questions);
        }

        return questionId;
    }

    private int addTestVariant(Set<TestVariantDto> testVariants, ResultSet resultSet) throws SQLException {
        int testVariantId = resultSet.getInt("test_variant_id");

        TestVariantDto testVariant = new TestVariantDto(testVariantId, resultSet.getString("variant"));
        testVariants.add(testVariant);

        return testVariantId;
    }
}
