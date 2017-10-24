package dao;

import classes.Role;
import classes.dto.TokenSavedInfo;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import exceptions.RegistrationTokenNotFoundException;
import exceptions.RegistrationTokenDAOException;
import interfaces.dao.RegistrationTokenDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationTokenDAOImpl implements RegistrationTokenDAO {

    private static Logger logger = Logger.getLogger(RegistrationTokenDAOImpl.class);
    private static ConnectionPool pool = TomcatConnectionPool.getInstance();

    private static final String GET_BY_TOKEN = "SELECT * FROM registration_token WHERE token = ?";
    private static final String GET_INFO_BY_TOKEN =
            "SELECT rt.token, rt.role, r.name as rname, c.name as cname, s.name as sname, sc.name as scname "
            + " FROM registration_token rt"
            + " JOIN schools s on rt.school_id = s.id"
            + " JOIN city c on s.city_id = c.id"
            + " JOIN region r on c.region_id = r.id"
            + " LEFT JOIN school_classes sc ON rt.school_class_id = sc.id"
            + " WHERE token = ?";

    @Override
    public Role getRoleByUrl(String url) throws RegistrationTokenDAOException, RegistrationTokenNotFoundException {
        Role result = null;
        try (Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_BY_TOKEN)) {
            statement.setString(1, url);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                result = Role.valueOf(set.getString("role"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RegistrationTokenDAOException(e);
        }
        if (result == null) {
            throw new RegistrationTokenNotFoundException();
        }
        return result;
    }

    @Override
    public TokenSavedInfo getSavedInfoByToken(String token) throws RegistrationTokenDAOException, RegistrationTokenNotFoundException {
        TokenSavedInfo info = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_INFO_BY_TOKEN)) {
            statement.setString(1, token);
            ResultSet set = statement.executeQuery();
            info = getInfoFromSet(set);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RegistrationTokenDAOException(e);
        }
        if (info == null) {
            throw new RegistrationTokenNotFoundException();
        }
        return info;
    }

    TokenSavedInfo getInfoFromSet(ResultSet set) throws SQLException {
        if (set.next()) {
            return new TokenSavedInfo(
                    set.getString("token"),
                    Role.valueOf(set.getString("role")),
                    set.getString("rname"),
                    set.getString("cname"),
                    set.getString("sname"),
                    set.getString("scname"));
        } else {
            return null;
        }
    }
}
