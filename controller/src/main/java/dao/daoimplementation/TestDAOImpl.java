package dao.daoimplementation;

import dao.dto.TestDTO;
import dao.dto.TestsDTO;
import dao.dto.WorkPageDTO;
import dao.TestDAO;
import com.google.common.base.Strings;
import connectionmanager.ConnectionManager;
import connectionmanager.ConnectionManagerPostgresImpl;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestDAOImpl implements TestDAO {
    private static final Logger logger = Logger.getLogger(TestDAOImpl.class);
    private static ConnectionManager manager;

    static {
        manager = ConnectionManagerPostgresImpl.getInstance();
    }

    @Override
    public List<TestsDTO> getTestsForVerifier(int verifierId) {
        List<TestsDTO> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = manager.getConnection().prepareStatement(
                    "select works.id, works.verification_deadline,school_classes.number,subjects.name from works\n" +
                            "left join students on students.id = works.student_id\n" +
                            "left join school_classes on students.school_class_id = school_classes.id\n" +
                            "left join test_templates on works.test_id = test_templates.id\n" +
                            "left join subjects on test_templates.subject_id = subjects.id\n" +
                            "where verifier_id  = ? order by works.verification_deadline");
            preparedStatement.setInt(1, verifierId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                Date deadLine = resultSet.getDate(2);
                int classNum = resultSet.getInt(3);
                String subjectName = resultSet.getString(4);
                TestsDTO testsDTO = new TestsDTO(id, classNum, subjectName, deadLine);
                result.add(testsDTO);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }


    @Override
    public TestDTO getTestInfoAndWorkIdsByTestId(int testId) {
        TestDTO result = null;
        try {
            PreparedStatement preparedStatement = manager.getConnection().prepareStatement(
                    "SELECT works.id, test_templates.topic, test_templates.description\n" +
                            "FROM works\n" +
                            "LEFT JOIN tests ON works.test_id = tests.id\n" +
                            "LEFT JOIN test_templates ON tests.test_template_id = test_templates.id\n" +
                            "WHERE test_id = ?");
            preparedStatement.setInt(1, testId);
            ResultSet resultSet = preparedStatement.executeQuery();
            String topic = "";
            String description = "";
            List<Integer> ids = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                ids.add(id);
                if (Strings.isNullOrEmpty(topic)) {
                    topic = resultSet.getString(2);
                }

                if (Strings.isNullOrEmpty(description)) {
                    description = resultSet.getString(3);
                }
                result = new TestDTO(topic, description, ids);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public WorkPageDTO getWorkPagesByWorkId(int workId) {
        WorkPageDTO result = new WorkPageDTO();
        try {
            PreparedStatement preparedStatement = manager.getConnection().prepareStatement(
                    "SELECT work_pages.file_url from work_pages where work_pages.work_id = ?");
            preparedStatement.setInt(1, workId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> urls = new ArrayList<>();
            while (resultSet.next()) {
                String url = resultSet.getString(1);
                urls.add(url);
            }
            result.setUrls(urls);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }
}
