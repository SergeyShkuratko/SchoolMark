package inno.dao;

import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import inno.classes.WorkStatus;
import inno.exceptions.OrganizerDAOexception;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WorkDAO {

    private Logger logger;
    private ConnectionPool pool;

    public WorkDAO() {
        this.logger = Logger.getLogger(WorkDAO.class);
        this.pool = TomcatConnectionPool.getInstance();
    }
    /**
     * Устанавливает признак присутствия ученика по ID работы
     *
     * @param workId
     * @param presence
     * @return
     * @throws OrganizerDAOexception
     */
    public boolean updatePresenceStatusByID(int workId, boolean presence) throws OrganizerDAOexception {
        final String SQL = "UPDATE works SET student_presence = ? WHERE id= ?";
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL)) {

            ps.setBoolean(1, presence);
            ps.setInt(2, workId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new OrganizerDAOexception(e);
        }
    }

    /**
     * Возвращает список всех статусов работы для определенной контрольной (по её ID)
     *
     * @param testId
     * @return
     * @throws OrganizerDAOexception
     */
    public List getWorksStatusByTest(int testId) throws OrganizerDAOexception {
        final String SQL = "SELECT id, status FROM works WHERE test_id=?";
        List<WorkStatus> statusList = new ArrayList<>();

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, testId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                statusList.add(new WorkStatus(rs.getInt("id"),
                        rs.getString("status")));
            }
            return statusList;
        } catch (SQLException e) {
            logger.error(e);
            throw new OrganizerDAOexception(e);
        }

    }

    /**
     * Возвращает список URL загруженных изображений работы
     *
     * @param workId
     * @return
     * @throws OrganizerDAOexception
     */
    public List<String> getPagesImgByWork(int workId) throws OrganizerDAOexception {
        List<String> imagesList = new ArrayList<>();
        final String SQL = "SELECT file_url FROM work_pages WHERE work_id=?";

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, workId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                imagesList.add(
                        rs.getString("file_url"));
            }
            return imagesList;
        } catch (SQLException e) {
            logger.error(e);
            throw new OrganizerDAOexception(e);
        }
    }

    /**
     * Перезаписывает статус работы
     *
     * @param id
     * @param status
     * @return
     * @throws OrganizerDAOexception
     */
    public int updateStatusById(int id, String status) throws OrganizerDAOexception {
        String sql = "UPDATE works SET status = ?::work_status WHERE id= ?";
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new OrganizerDAOexception(e);
        }
    }
}
