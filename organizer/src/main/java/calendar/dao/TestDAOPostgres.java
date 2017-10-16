package calendar.dao;

import calendar.dao.exceptions.TestDAOException;
import classes.Teacher;
import calendar.dto.TestDTO;
import connectionmanager.ConnectionManager;
import connectionmanager.ConnectionManagerPostgresImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestDAOPostgres implements TestDAO {
    Logger logger = Logger.getLogger(TestDAOPostgres.class);

    @Override
    public List<TestDTO> getTestsDTOByTeacherDuringPeriod(Teacher teacher, LocalDate begin, LocalDate end) throws TestDAOException {
        ConnectionManager connectionManager = ConnectionManagerPostgresImpl.getInstance();
        List<TestDTO> result = new ArrayList<>();
        try {
            Connection connection = connectionManager.getConnection();
            try (PreparedStatement ps = connection.prepareStatement("select subjects.name as subject, tests.* from test_templates " +
                    "   join tests on tests.test_template_id = test_templates.id " +
                    "   left join subjects on test_templates.subject_id = subjects.id " +
                    "   where tests.owner_id = ?");) {
                ps.setInt(1, teacher.getId());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    TestDTO testDTO = new TestDTO(rs.getInt("id"));
                    testDTO.setOwnerId(rs.getInt("owner_id"));
                    testDTO.setSchoolClassId(rs.getInt("school_class_id"));
                    testDTO.setStartDate(rs.getDate("start_date_time").toLocalDate());
                    testDTO.setStatus(rs.getString("status"));
                    testDTO.setTemplateId(rs.getInt("test_template_id"));
                    testDTO.setVerificationDate(rs.getDate("verification_deadline").toLocalDate());
                    testDTO.setSubject(rs.getString("subject"));
                    result.add(testDTO);
                }
            }
        } catch (SQLException e) {
            logger.error("Ошибка получения тестов. " + e.getLocalizedMessage(), e);
            throw new TestDAOException("Ошибка получения тестов. " + e.getLocalizedMessage(), e);
        }
        return result;
    }
}
