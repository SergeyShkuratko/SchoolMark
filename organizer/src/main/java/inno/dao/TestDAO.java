package inno.dao;

import connectionmanager.ConnectionManager;
import connectionmanager.ConnectionManagerPostgresImpl;
import inno.exceptions.OrganizerDAOexception;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDAO {
    private static ConnectionManager manager = ConnectionManagerPostgresImpl.getInstance();
    public static boolean updateTestStatusByID(int id, String status) throws OrganizerDAOexception {
        String sql = "UPDATE tests SET status = ? WHERE id= ?";
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
