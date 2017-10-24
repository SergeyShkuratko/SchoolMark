package inno.dao;

import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import inno.exceptions.OrganizerDAOexception;
import org.apache.log4j.Logger;
import org.postgresql.util.PGobject;

import java.sql.*;
import java.time.LocalDate;

public class TestDAO {

    private static Logger logger = Logger.getLogger(TestDAO.class);

    private static ConnectionPool pool = TomcatConnectionPool.getInstance();

    public static boolean updateTestStatusByID(int id, String status) throws OrganizerDAOexception {
        System.out.println("go");
        final String sql = "UPDATE tests SET status = ? WHERE id= ?";
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(2, id);
            PGobject status_var = new PGobject();
            status_var.setType("test_status");
            status_var.setValue("uploaded");
            ps.setObject(1, status_var);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new OrganizerDAOexception(e);
        }
    }


    public static boolean doneTest(int id) throws OrganizerDAOexception {
       final String sql = "UPDATE tests SET status = ?, verification_deadline = ? WHERE id= ?";
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(3, id);
            PGobject status_var = new PGobject();
            status_var.setType("test_status");
            status_var.setValue("uploaded");

            ps.setDate(2, java.sql.Date.valueOf(LocalDate.now().plusDays(7)));

            ps.setObject(1, status_var);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new OrganizerDAOexception(e);
        }
    }


}
