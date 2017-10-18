package inno.dao;

import connectionmanager.ConnectionManager;
import connectionmanager.ConnectionManagerPostgresImpl;
import inno.dto.TestDTO;
import inno.exceptions.OrganizerDAOexception;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public static Map<Integer, String> getWorksStatusByTestId(int test_id) throws OrganizerDAOexception {
        String sql = "SELECT id, status FROM works WHERE test_id=" + test_id;

        try {
            Map<Integer, String> map = new HashMap<>();

            Statement statement = manager.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                map.put(
                        rs.getInt("id"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            throw new OrganizerDAOexception(e);
        }
        return null;
    }

}
