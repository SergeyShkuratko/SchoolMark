package controller.dao.daoimplementation;

import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import controller.dao.VerificationDAO;
import controller.dao.dto.VerificationResultDTO;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class VerificationDAOImpl implements VerificationDAO {
    private static final Logger logger = Logger.getLogger(TestDAOImpl.class);
    private static ConnectionPool manager;

    static {
        manager = TomcatConnectionPool.getInstance();
    }

    @Override
    public boolean persistVerificationResult(VerificationResultDTO result) {
        try {
            PreparedStatement preparedStatement = manager.getConnection()
                    .prepareStatement("INSERT INTO public.verification_results(date_time, comment, mark, work_id, verifier_id)\n" +
                            "    SELECT ?, ?, ?, ?, t.id FROM teachers t WHERE t.user_id = ?");
            preparedStatement.setTimestamp(1, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            preparedStatement.setString(2, result.getComment());
            preparedStatement.setInt(3, result.getMark());
            preparedStatement.setInt(4, result.getWorkId());
            preparedStatement.setInt(5, result.getVerifierId());
            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }
}
