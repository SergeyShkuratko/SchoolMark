package inno.dao;

import connectionmanager.ConnectionManager;
import connectionmanager.ConnectionManagerPostgresImpl;
import inno.exceptions.OrganizerDAOexception;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WorkDAO {
    private static ConnectionManager manager = ConnectionManagerPostgresImpl.getInstance();
    public static boolean  updatePresenceStatusByID(int work_id, boolean presence) throws OrganizerDAOexception {
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
}
