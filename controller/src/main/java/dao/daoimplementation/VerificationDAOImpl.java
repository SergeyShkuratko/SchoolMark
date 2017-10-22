package dao.daoimplementation;

import dao.dto.VerificationResultDTO;
import dao.VerificationDAO;
import connectionmanager.ConnectionManager;
import connectionmanager.ConnectionManagerPostgresImpl;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class VerificationDAOImpl implements VerificationDAO {
    private static final Logger logger = Logger.getLogger(TestDAOImpl.class);
    private static ConnectionManager manager;

    static {
        manager = ConnectionManagerPostgresImpl.getInstance();
    }

    @Override
    public boolean persistVerificationResult(VerificationResultDTO result) {
        try {
            PreparedStatement preparedStatement = manager.getConnection().prepareStatement("INSERT INTO public.verification_results(\n" +
                    "             date_time, comment, mark, work_id, verifier_id)\n" +
                    "    VALUES (?, ?, ?, ?, ?);\n");
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
