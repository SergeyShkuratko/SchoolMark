package inno.dao;

import com.google.gson.JsonObject;
import connectionmanager.ConnectionManager;
import connectionmanager.ConnectionManagerPostgresImpl;
import inno.exceptions.OrganizerDAOexception;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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


    public static JsonObject getJsonWorkStatusByTestId(int test_id) throws OrganizerDAOexception {
        String sql = "SELECT id, status FROM works WHERE test_id=" + test_id;

        try {
            JsonObject json = new JsonObject();

            Statement statement = manager.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                json.addProperty(
                        ""+rs.getInt("id"),
                        rs.getString("status")
                );
            }
            return json;
        } catch (SQLException e) {
            throw new OrganizerDAOexception(e);
        }

    }

}
