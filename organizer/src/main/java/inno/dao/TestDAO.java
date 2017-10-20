package inno.dao;

import connectionmanager.ConnectionManager;
import connectionmanager.ConnectionManagerPostgresImpl;
import inno.exceptions.OrganizerDAOexception;
import org.postgresql.util.PGobject;

import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.List;

public class TestDAO {
    private static ConnectionManager manager = ConnectionManagerPostgresImpl.getInstance();
    public static boolean updateTestStatusByID(int id, String status) throws OrganizerDAOexception {
        System.out.println("go");
        String sql = "UPDATE tests SET status = ? WHERE id= ?";
        try {
            PreparedStatement ps = manager.getConnection().prepareStatement(sql);

            ps.setInt(2, id);
            PGobject status_var = new PGobject();
            status_var.setType("test_status");
            status_var.setValue("uploaded");
            ps.setObject(1, status_var);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new OrganizerDAOexception(e);
        }
    }

}
