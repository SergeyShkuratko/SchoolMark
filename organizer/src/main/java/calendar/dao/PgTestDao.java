package calendar.dao;

import calendar.dao.exceptions.TestDAOException;
import calendar.dto.TestDto;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PgTestDao implements TestDao {

    @Override
    public List<TestDto> getTestsByUserIdDescOrder(int userId, LocalDate begin, LocalDate end) throws TestDAOException {
        ConnectionPool connectionManager = TomcatConnectionPool.getInstance();
        List<TestDto> result = new ArrayList<>();
        try {
            Connection connection = connectionManager.getConnection();
            String sqlQuery = "select subjects.name as subject, tests.* " +
                    "   from tests " +
                    "   join test_templates on tests.test_template_id = test_templates.id " +
                    "   left join subjects on test_templates.subject_id = subjects.id " +
                    "   where tests.owner_id = (select id from teachers where user_id=?) " +
                    "   order by start_date_time DESC";
            try (PreparedStatement ps = connection.prepareStatement(sqlQuery);) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    TestDto testDto = new TestDto(rs.getInt("id"));
                    testDto.setOwnerId(rs.getInt("owner_id"));
                    testDto.setSchoolClassId(rs.getInt("school_class_id"));
                    testDto.setStartDate(rs.getDate("start_date_time").toLocalDate());
                    testDto.setStatus(rs.getString("status"));
                    testDto.setTemplateId(rs.getInt("test_template_id"));
                    testDto.setVerificationDate(rs.getDate("verification_deadline").toLocalDate());
                    testDto.setSubject(rs.getString("subject"));
                    result.add(testDto);
                }
            }
        } catch (SQLException e) {
            throw new TestDAOException("Ошибка получения тестов. " + e.getLocalizedMessage(), e);
        }
        return result;
    }
}
