package dao;

import classes.Role;
import classes.User;
import classes.UserCredentials;
import connectionmanager.ConnectionManager;
import connectionmanager.ConnectionManagerPostgresImpl;
import exceptions.UserDAOException;
import exceptions.UserNotFoundException;
import interfaces.dao.UserDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    Logger logger = Logger.getLogger(UserDAOImpl.class);
    @Override
    public User getByCredentials(UserCredentials credentials) throws UserNotFoundException {
        ConnectionManager connectionManager = ConnectionManagerPostgresImpl.getInstance();
        User result = null;
        try {
            Connection connection = connectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT ID, REGISTRATION_DATE FROM USERS WHERE LOGIN=? AND PASSWORD=?");
            ps.setString(1, credentials.getLogin());
            ps.setString(2, credentials.getPasswordHash());
            ResultSet rs = ps.executeQuery();
            if(!rs.next()) throw new UserNotFoundException("Пользователь с заданным логином и паролем не найден.");
            result = new User(rs.getInt("id"), credentials.getLogin(), rs.getDate("registration_date").toLocalDate());
        } catch (SQLException e) {
            logger.error(e.getLocalizedMessage(), e);
            throw new UserNotFoundException(e);
        }
        return result;
    }

    @Override
    public User register(UserCredentials credentials) throws UserDAOException {
//        ConnectionManager connectionManager = ConnectionManagerPostgresImpl.getInstance();
//        User result = null;
//        try {
//            Connection connection = connectionManager.getConnection();
//            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
//            connection.setAutoCommit(false);
//            PreparedStatement ps = connection.prepareStatement("SELECT ID FROM USERS WHERE LOGIN=? AND PASSWORD=?");
//            ps.setString(1, credentials.getLogin());
//            ps.setString(2, credentials.getPasswordHash());
//            ResultSet rs = ps.executeQuery();
//            if(rs.next()){
//                connection.setAutoCommit(true);
//                connection.setTransactionIsolation(Connection.TRANSACTION_NONE);
//                throw new UserDAOException("Пользователь с заданным логином и паролем уже существует.");
//            }
//            ps = connection.prepareStatement("INSERT INTO USERS(LOGIN, PASSWORD, ROLE)")
//        } catch (SQLException e) {
//            logger.error(e.getLocalizedMessage(), e);
//            throw new UserNotFoundException(e);
//        }
        return null;
    }

    @Override
    public Role getRole() {
        return null;
    }

    @Override
    public boolean update(User user) throws UserDAOException {
        return false;
    }
}
