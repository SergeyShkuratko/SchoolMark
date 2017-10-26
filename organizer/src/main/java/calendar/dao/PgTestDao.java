package calendar.dao;

import calendar.dao.exceptions.TestDAOException;
import calendar.dto.TestDTO;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PgTestDao implements TestDao {
    private static ConnectionPool pool = TomcatConnectionPool.getInstance();

    @Override
    public Map<LocalDate, List<TestDTO>> getTestsByUserIdGroupByDate(int userId, LocalDate begin, LocalDate end) throws TestDAOException {

        Map<LocalDate, List<TestDTO>> result = new HashMap<>();
        String sqlQuery = "select subjects.name as subject, tests.* " +
                "   from tests " +
                "   join test_templates on tests.test_template_id = test_templates.id " +
                "   left join subjects on test_templates.subject_id = subjects.id " +
                "   where tests.owner_id = (select id from teachers where user_id=?) " +
                "   order by start_date_time DESC";
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TestDTO testDTO = new TestDTO(rs.getInt("id"));
                testDTO.setOwnerId(rs.getInt("owner_id"));
                testDTO.setSchoolClassId(rs.getInt("school_class_id"));
                testDTO.setStartDate(rs.getTimestamp("start_date_time").toLocalDateTime());
                testDTO.setStatus(rs.getString("status"));
                testDTO.setTemplateId(rs.getInt("test_template_id"));
                Timestamp t = rs.getTimestamp("verification_deadline");
                testDTO.setVerificationDate(t == null ? testDTO.getStartDate() : t.toLocalDateTime());
                testDTO.setSubject(rs.getString("subject"));
                if (!result.containsKey(testDTO.getStartDate().toLocalDate())) {
                    result.put(testDTO.getStartDate().toLocalDate(), new ArrayList<>());
                }
                result.get(testDTO.getStartDate().toLocalDate()).add(testDTO);
            }
        } catch (SQLException e) {
            throw new TestDAOException("Ошибка получения тестов. " + e.getLocalizedMessage(), e);
        }
        return result;
    }
}
