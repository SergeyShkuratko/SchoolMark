package com.inno.db.dao;

import com.inno.db.dto.TestAndWorksInfo;
import com.inno.db.dto.TestStatisticDto;
import com.inno.db.exception.DaoException;
import connectionmanager.ConnectionManager;
import connectionmanager.ConnectionManagerPostgresImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PgTestDao implements TestDao {
    private ConnectionManager connectionManager;

    public PgTestDao() {
        this.connectionManager = ConnectionManagerPostgresImpl.getInstance();
    }

    @Override
    public List<TestStatisticDto> getTestsStatistic() {
        try {
            ResultSet resultSet = connectionManager.getConnection()
                    .createStatement()
                    .executeQuery(
                    "SELECT t.start_date_time::date as date, " +
                            "concat_ws(' ', tr.last_name, tr.first_name, tr.patronymic) as organizer, " +
                            "sbj.name as subject, cl.name as class_name, AVG(vr.mark) average_mark " +
                            "FROM tests t " +
                            "JOIN teachers tr ON t.owner_id = tr.id " +
                            "JOIN works w ON w.test_id = t.id " +
                            "JOIN test_templates tt ON t.test_template_id = tt.id " +
                            "JOIN school_classes cl ON t.school_class_id = cl.id " +
                            "JOIN verification_results vr ON vr.work_id = w.id " +
                            "JOIN subjects sbj ON tt.subject_id = sbj.id " +
                            "GROUP BY date, organizer, subject, class_name");

            List<TestStatisticDto> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(
                        new TestStatisticDto(
                                resultSet.getDate("date").toLocalDate(),
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
    public TestAndWorksInfo getTestAndWorksInfo(int testId) {
        try {
            PreparedStatement statement = connectionManager.getConnection()
                    .prepareStatement("SELECT q.id, q.question, q.answer, q.criteria, " +
                            "concat_ws(' ', st.last_name, st.first_name, st.patronymic), vr.mark, " +
                            "w.status = work_status.  " +
                            "FROM tests t " +
                            "JOIN works w ON w.test_id = t.id " +
                            "JOIN test_templates tt ON t.test_template_id = t.id " +
                            "JOIN questions q ON q.test_template_id = tt.id " +
                            "JOIN students st ON w.student_id = st.id " +
                            "JOIN verification_results vr ON vr.work_id = w.id " +
                            "WHERE t.id = ?");
            statement.setInt(1, testId);
            ResultSet resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
