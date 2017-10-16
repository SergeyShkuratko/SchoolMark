package DAO.daoimplementation;

import DAO.DTO.VerificationResultDTO;
import DAO.VerificationDAO;
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
            preparedStatement.setString(2, result.comment);
            preparedStatement.setInt(3, result.mark);
            preparedStatement.setInt(4, result.workId);
            preparedStatement.setInt(5, result.verifierId);
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
