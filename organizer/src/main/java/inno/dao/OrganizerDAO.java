package inno.dao;

import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import inno.dto.TestDTO;
import inno.dto.WorkDTO;
import inno.exceptions.OrganizerDAOexception;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrganizerDAO {


    private static Logger logger = Logger.getLogger(OrganizerDAO.class);
    private static ConnectionPool pool = TomcatConnectionPool.getInstance();

    /**
     * Возвращает тест по его ID
     *
     * @param test_id
     * @return
     * @throws OrganizerDAOexception
     */
    public static TestDTO getTestById(int test_id) throws OrganizerDAOexception {
        final String SQL = "SELECT t.id, t.status, tt.topic, tt.description, sc.id AS school_class_id " +
                "FROM tests AS t " +
                "JOIN test_templates AS tt ON t.test_template_id = tt.id " +
                "JOIN school_classes AS sc ON t.school_class_id = sc.id " +
                "WHERE t.id=?";

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, test_id);
            ResultSet rs = statement.executeQuery();

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
            logger.error(e);
            throw new OrganizerDAOexception(e);
        }
        return null;
    }

    /**
     * Создает по работе для каждого ученика по указанному ID теста
     *
     * @param test_id
     * @return true/false
     * @throws OrganizerDAOexception
     */
    public static boolean createWorksForTest(int test_id) throws OrganizerDAOexception {
        final String SQL = "INSERT INTO works (test_id, student_id, status)" +
                "  SELECT" +
                "    t.id," +
                "    s.id," +
                "    'new'" +
                "  FROM tests AS t" +
                "    JOIN students s ON t.school_class_id = s.school_class_id" +
                "  WHERE t.id=?";
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, test_id);
//            return statement.execute(SQL);
            return statement.executeUpdate() == 1;

        } catch (SQLException e) {
            logger.error(e);
            throw new OrganizerDAOexception(e);
        }
    }

    /**
     * Проверяет, существуют ли работы для этой контрольной работы
     *
     * @param test_id
     * @return true/false
     * @throws OrganizerDAOexception
     */
    public static boolean isWorksExists(int test_id) throws OrganizerDAOexception {
        final String SQL = "SELECT COUNT(id) >= 1 " +
                "FROM works " +
                "WHERE test_id=?";

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, test_id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getBoolean(1);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new OrganizerDAOexception(e);
        }
        return false;
    }

    /**
     * Возвращает список работ по тесту
     *
     * @param test_id
     * @return лист работ
     */
    public static List<WorkDTO> getAllWorksByTestId(int test_id) throws OrganizerDAOexception {
        List<WorkDTO> list = new ArrayList<>();
        final String SQL = "SELECT w.id," +
                " w.student_id," +
                " w.status," +
                " concat(s.last_name, ' ', s.first_name, ' ', s.patronymic) as student_fullname " +
                "FROM works w " +
                "JOIN students s on w.student_id = s.id " +
                "WHERE w.test_id = ?";

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, test_id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                list.add(new WorkDTO(
                        rs.getInt("id"),
                        rs.getInt("student_id"),
                        rs.getString("student_fullName"),
                        rs.getString("status")
                ));
            }

            return list;

        } catch (SQLException e) {
            logger.error(e);
            throw new OrganizerDAOexception(e);
        }
    }

}
