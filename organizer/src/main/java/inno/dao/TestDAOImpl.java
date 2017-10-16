package inno.dao;

import classes.*;
import connectionmanager.ConnectionManager;
import connectionmanager.ConnectionManagerPostgresImpl;
import exceptions.TestDAOException;
import inno.dto.TestDTO;
import inno.exceptions.TestDTOException;
import interfaces.dao.TestDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class TestDAOImpl {

    private static ConnectionManager manager = ConnectionManagerPostgresImpl.getInstance();

    public static TestDTO getById(int id) throws TestDTOException {
        String sql = "SELECT t.id, t.status, tt.topic, tt.description, sc.id AS school_class_id " +
                "FROM tests AS t " +
                "JOIN test_templates AS tt ON t.test_template_id = tt.id " +
                "JOIN school_classes AS sc ON t.school_class_id = sc.id ";

        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return new TestDTO(
                        rs.getInt("id"),
                        rs.getString("status"),
                        rs.getString("topic"),
                        rs.getString("description"),
                        rs.getInt("school_class_id")
                );
            }
        } catch (SQLException e) {
            throw new TestDTOException(e);
        }
        return null;
    }

}
