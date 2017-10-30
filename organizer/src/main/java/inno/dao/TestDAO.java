package inno.dao;

import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import inno.exceptions.OrganizerDAOexception;
import org.apache.log4j.Logger;
import org.postgresql.util.PGobject;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;

@Repository
public class TestDAO {

    private Logger logger;
    private ConnectionPool pool;

    public TestDAO() {
        this.logger = Logger.getLogger(TestDAO.class);
        this.pool = TomcatConnectionPool.getInstance();
    }

    /**
     * Обновляет статус контрольной работы
     *
     * @param id     - id контрольной в таблице tests
     * @param status - статус из перечисления test_status
     * @return true если обновилось, false если нет
     * @throws OrganizerDAOexception
     */
    public boolean updateTestStatusById(int id, String status) throws OrganizerDAOexception {
        final String sql = "UPDATE tests SET status = ? WHERE id= ?";

        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(2, id);

            PGobject status_var = new PGobject();
            status_var.setType("test_status");
            status_var.setValue(status);


            ps.setObject(1, status_var);

            ps.executeUpdate();
            return ps.getUpdateCount() > 0;


        } catch (SQLException e) {
            logger.error(e);
            throw new OrganizerDAOexception(e);
        }
    }


    public boolean getTestByStatusAndId(Integer testId, String status) throws OrganizerDAOexception{
        final String SQL = "SELECT 1 FROM tests WHERE id=? and status = ?::test_status";

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, testId);
            statement.setString(2, status);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new OrganizerDAOexception(e);
        }
        return false;
    }
}
