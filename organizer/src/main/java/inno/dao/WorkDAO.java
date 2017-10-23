package inno.dao;

import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import inno.classes.WorkStatus;
import inno.exceptions.OrganizerDAOexception;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class WorkDAO {

    private static ConnectionPool pool = TomcatConnectionPool.getInstance();

    public static boolean updatePresenceStatusByID(int work_id, boolean presence) throws OrganizerDAOexception {
        String sql = "UPDATE works SET student_presence = ? WHERE id= ?";
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setBoolean(1, presence);
            ps.setInt(2, work_id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new OrganizerDAOexception(e);
        }
    }


    public static List getWorksStatusByTest(int test_id) throws OrganizerDAOexception {
        String sql = "SELECT id, status FROM works WHERE test_id=" + test_id;
        List<WorkStatus> statusList = new ArrayList<>();

        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {

                statusList.add(new WorkStatus(rs.getInt("id"),
                        rs.getString("status")));
            }
            return statusList;
        } catch (SQLException e) {
            throw new OrganizerDAOexception(e);
        }

    }

    public static List<String> getPagesImgByWork(int work_id) throws OrganizerDAOexception {
        List<String> imagesList = new ArrayList<>();
        String sql = "SELECT file_url FROM work_pages WHERE work_id=" + work_id;
        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                imagesList.add(
                        rs.getString("file_url"));
            }
            return imagesList;
        } catch (SQLException e) {
            throw new OrganizerDAOexception(e);
        }
    }

    public static int updateStatusById(int id, String status) throws OrganizerDAOexception {
        String sql = "UPDATE works SET status = ?::work_status WHERE id= ?";
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, id);
            return  ps.executeUpdate();
        } catch (SQLException e) {
            throw new OrganizerDAOexception(e);
        }
    }
}
