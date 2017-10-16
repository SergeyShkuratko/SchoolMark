package inno.dao;

import connectionmanager.ConnectionManager;
import connectionmanager.ConnectionManagerPostgresImpl;
import inno.dto.TestDTO;
import inno.exceptions.OrganizerDAOexception;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrganizerDAO {

    private static ConnectionManager manager = ConnectionManagerPostgresImpl.getInstance();

    /**
     * Возвращает тест по его ID
     *
     * @param id
     * @return
     * @throws OrganizerDAOexception
     */
    public static TestDTO getTestById(int id) throws OrganizerDAOexception {
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
            throw new OrganizerDAOexception(e);
        }
        return null;
    }

    /**
     * Создает по работе для каждого ученика по указанному ID теста
     *
     * @param test_id
     * @return
     */
    public static boolean createWorksForTest(int test_id) throws OrganizerDAOexception {
        String sql = "INSERT INTO works (test_id, student_id, status)" +
                "  SELECT" +
                "    t.id," +
                "    s.id," +
                "    'new'" +
                "  FROM tests AS t" +
                "    JOIN students s ON t.school_class_id = s.school_class_id" +
                "  WHERE t.id=" + test_id;
        try {
            return manager.getConnection().createStatement().execute(sql);
        } catch (SQLException e) {
            throw new OrganizerDAOexception(e);
        }
    }

}
