package inno.dao;

import connectionmanager.ConnectionManager;
import connectionmanager.ConnectionManagerPostgresImpl;
import inno.exceptions.OrganizerDAOexception;
import org.postgresql.util.PGobject;

import java.sql.*;
import java.time.LocalDate;
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


    public static boolean doneTest(int id) throws OrganizerDAOexception {
        System.out.println("go");
        String sql = "UPDATE tests SET status = ?, verification_deadline = ? WHERE id= ?";
        try {
            PreparedStatement ps = manager.getConnection().prepareStatement(sql);

            ps.setInt(3, id);
            PGobject status_var = new PGobject();
            status_var.setType("test_status");
            status_var.setValue("uploaded");

            ps.setDate(2, java.sql.Date.valueOf(LocalDate.now().plusDays(7)));

            ps.setObject(1, status_var);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new OrganizerDAOexception(e);
        }
    }


}
