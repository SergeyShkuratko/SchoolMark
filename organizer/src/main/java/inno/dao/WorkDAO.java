package inno.dao;

import com.google.gson.JsonObject;
import connectionmanager.ConnectionManager;
import connectionmanager.ConnectionManagerPostgresImpl;
import inno.classes.WorkStatus;
import inno.exceptions.OrganizerDAOexception;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class WorkDAO {
    private static ConnectionManager manager = ConnectionManagerPostgresImpl.getInstance();

    public static boolean updatePresenceStatusByID(int work_id, boolean presence) throws OrganizerDAOexception {
        String sql = "UPDATE works SET student_presence = ? WHERE id= ?";
        try {
            PreparedStatement ps = manager.getConnection().prepareStatement(sql);
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
        try {

            Statement statement = manager.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
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
        try {

            Statement statement = manager.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                imagesList.add(
                        rs.getString("file_url"));
            }
            return imagesList;
        } catch (SQLException e) {
            throw new OrganizerDAOexception(e);
        }
    }

    public static Object updateStatusById(int id, String status) throws OrganizerDAOexception {
        String sql = "UPDATE works SET status = ? WHERE id= ?";
        try {
            PreparedStatement ps = manager.getConnection().prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new OrganizerDAOexception(e);
        }
    }
}
