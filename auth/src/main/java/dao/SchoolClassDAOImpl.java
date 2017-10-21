package dao;

import classes.SchoolClass;
import classes.dto.SchoolClassDTO;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import exceptions.SchoolClassDAOException;
import interfaces.dao.SchoolClassDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SchoolClassDAOImpl implements SchoolClassDAO {

    private static Logger logger = Logger.getLogger(SchoolClassDAOImpl.class);
    private static ConnectionPool pool = TomcatConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO school_classes (number, name, school_id)" +
            " VALUES (?, ?, ?)";

    @Override
    public boolean persistSchool(SchoolClassDTO schoolClass) throws SchoolClassDAOException {
        boolean result = false;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setInt(1, schoolClass.num);
            statement.setString(2, schoolClass.name);
            statement.setInt(3, schoolClass.schoolId);
            statement.execute();
            result = true;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new SchoolClassDAOException(e);
        }
        return result;
    }
}
