package inno.dto;

import connectionmanager.ConnectionManager;
import connectionmanager.ConnectionManagerPostgresImpl;
import inno.exceptions.TestDTOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDTO {
   private int id;
   private String status;
   private String topic;
   private String description;
   private int school_class_id;


    public TestDTO(int id, String status, String topic, String description, int school_class_id) {
        this.id = id;
        this.status = status;
        this.topic = topic;
        this.description = description;
        this.school_class_id = school_class_id;
    }

    private ConnectionManager manager = ConnectionManagerPostgresImpl.getInstance();

    public TestDTO getById(int id) throws TestDTOException {
        String sql = "SELECT t.id, t.status, tt.topic, tt.description, sc.id AS school_class_id " +
                "FROM tests AS t " +
                "JOIN test_templates AS tt ON t.test_template_id = tt.id " +
                "JOIN school_classes AS sc ON t.school_class_id = sc.id ";

        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return new TestDTO(
                        rs.getInt(id),
                        rs.getString(status),
                        rs.getString(topic),
                        rs.getString(description),
                        rs.getInt(school_class_id)
                );
            }
        } catch (SQLException e) {
            throw new TestDTOException(e);
        }
        return null;
    }
}
