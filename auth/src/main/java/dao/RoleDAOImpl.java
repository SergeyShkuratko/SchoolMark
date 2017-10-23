package dao;

import classes.Role;
import classes.dto.SchoolDTO;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import exceptions.RegistrationTokenNotFoundException;
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
    private static final String GET_BY_ROLE_AND_SCHOOL =
            "SELECT token FROM registration_token WHERE role = ?::role and school_id = ?";
    private static final String INSERT_FOR_ROLE_AND_SCHOOL =
            "INSERT INTO registration_token (token, role, school_id) VALUES (?, ?::role, ?)";

    @Override
    public Role getRoleByToken(String token) throws RoleDAOException, RegistrationTokenNotFoundException {
        Role result = null;
        try (Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_BY_URL)) {
            statement.setString(1, token);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                result = Role.valueOf(set.getString("role"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RoleDAOException(e);
        }
        if (result == null) {
            throw new RegistrationTokenNotFoundException();
        }
        return result;
    }

    public String getTokenByRoleAndSchool(Role role, int schoolId) throws RoleDAOException, RegistrationTokenNotFoundException {
        String result;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ROLE_AND_SCHOOL)) {
            statement.setString(1, role.toString());
            statement.setInt(2, schoolId);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                result = set.getString("token");
            } else {
                throw new RegistrationTokenNotFoundException("Registration token not found for role " + role + " and schoolId = " + schoolId);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RoleDAOException(e);
        } catch (RegistrationTokenNotFoundException e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        return result;
    }

    @Override
    public void setTokenForRoleAndSchool(String token, Role role, int schoolId) throws RoleDAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_FOR_ROLE_AND_SCHOOL)) {
            statement.setString(1, token);
            statement.setString(2, role.toString());
            statement.setInt(3, schoolId);
            int result = statement.executeUpdate();
            if (result == 0) {
                throw new RoleDAOException();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RoleDAOException(e);
        }
    }
}
