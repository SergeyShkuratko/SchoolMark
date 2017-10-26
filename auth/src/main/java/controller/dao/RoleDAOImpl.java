package controller.dao;

import classes.Role;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import exceptions.RegisterUrlNotFoundException;
import exceptions.RoleDAOException;
import interfaces.dao.RoleDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAOImpl implements RoleDAO {

    private static Logger logger = Logger.getLogger(RoleDAOImpl.class);
    private static ConnectionPool pool = TomcatConnectionPool.getInstance();

    private static final String GET_BY_URL = "SELECT * FROM registration_token WHERE token = ?";

    @Override
    public Role getRoleByUrl(String url) throws RoleDAOException, RegisterUrlNotFoundException {
        Role result = null;
        try (Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_BY_URL)) {
            statement.setString(1, url);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                result = Role.valueOf(set.getString("role"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RoleDAOException(e);
        }
        if (result == null) {
            throw new RegisterUrlNotFoundException();
        }
        return result;
    }
}
