package dao;

import classes.Role;
import classes.User;
import classes.UserCredentials;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import exceptions.DAOException;
import exceptions.UserDAOException;
import exceptions.UserNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import security.CustomUser;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

@Component
public class UserDAOImpl implements UserDAO {
    private static Logger logger = Logger.getLogger(UserDAOImpl.class);

    private static final String GET_BY_CRED = "SELECT * FROM users u" +
            " LEFT JOIN teachers t ON t.user_id = u.id" +
            " LEFT JOIN students s ON u.id = s.user_id" +
            " WHERE login = ? and password = ?";
    private static final String INSERT = "INSERT INTO users" +
                    " (login, password, role, registration_date, last_login_date)" +
                    " VALUES (?, ?, ?::role, ?, ?)";

    private ConnectionPool dataSource = TomcatConnectionPool.getInstance();

    @Override
    public User getByCredentials(UserCredentials credentials) throws UserNotFoundException, UserDAOException {
        User user = null;
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_BY_CRED)) {

            statement.setString(1, credentials.getLogin());
            statement.setString(2, credentials.getPasswordHash());
            ResultSet set = statement.executeQuery();
            user = userFromResultSet(set);
        } catch (SQLException | UserDAOException e) {
            logger.error(e.getMessage(), e);
            throw new UserDAOException(e);
        }
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public User register(UserCredentials credentials, Role role) throws UserDAOException {
        User user = null;
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, credentials.getLogin());
            statement.setString(2, credentials.getPasswordHash());
            statement.setString(3, role.name());
            statement.setDate(4, Date.valueOf(LocalDate.now()));
            statement.setDate(5, Date.valueOf(LocalDate.now()));

            if (statement.executeUpdate() > 0) {
                ResultSet set = statement.getGeneratedKeys();
                if (set.next()) {
                    user = new User(set.getInt(1),
                            credentials.getLogin(),
                            LocalDate.now());
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new UserDAOException(e);
        }
        if (user == null) {
            throw new UserDAOException();
        }
        return user;
    }

    @Override
    public Optional<CustomUser> getByUsername(String username) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(
                             "SELECT id, password, 'ROLE_' || upper(role::text) as role FROM users WHERE login = ?")) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                CustomUser user =
                        new CustomUser(
                                resultSet.getInt("id"),
                                username,
                                resultSet.getString("password"),
                                Collections.singleton(
                                        new SimpleGrantedAuthority(
                                                resultSet.getString("role"))));
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DAOException("Exception occured while getting user by username: " + username, e);
        }
    }

    private User userFromResultSet(ResultSet set) throws UserDAOException {
        User result = null;

        try {
            if (set.next()) {
                result = new User(
                        set.getInt("id"),
                        set.getString("login"),
                        set.getDate("registration_date").toLocalDate(),
                        Role.valueOf(set.getString("role")));
            }
        } catch (SQLException e) {
            throw new UserDAOException(e.fillInStackTrace());
        }
        return result;
    }
}
