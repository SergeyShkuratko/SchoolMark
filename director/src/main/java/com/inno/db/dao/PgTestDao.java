package com.inno.db.dao;

import com.inno.db.dto.TestDto;
import com.inno.db.exception.DaoException;
import org.postgresql.ds.PGConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PgTestDao implements TestDao {
    @Override
    public List<TestDto> findAll() {
        try {
            PGConnectionPoolDataSource connectionPool = new PGConnectionPoolDataSource();
            Connection connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT t.start_date_time::date as date, " +
                            "concat_ws(',', tr.last_name, tr.first_name, tr.patronymic) as organizer, " +
                            "sbj.name as subject, cl.name as class_name, AVG(vr.mark) average_mark " +
                            "FROM tests t " +
                            "JOIN teachers tr ON t.owner_id = tr.id " +
                            "JOIN works w ON w.test_id = t.id " +
                            "JOIN test_templates tt ON t.test_template_id = tt.id " +
                            "JOIN school_classes cl ON t.school_class_id = cl.id " +
                            "JOIN verification_results vr ON vr.work_id = w.id " +
                            "JOIN subjects sbj ON tt.subject_id = sbj.id");

            List<TestDto> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(
                        new TestDto(
                                resultSet.getDate("date").toLocalDate(),
                                resultSet.getString("organizer"),
                                resultSet.getString("subject"),
                                resultSet.getString("class_name"),
                                resultSet.getFloat("average_mark")));
            }

            return result;
        } catch (SQLException e) {
            throw new DaoException("Exception occured while getting test statistic from database", e);
        }
    }
}
