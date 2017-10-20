package connectionmanager;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManagerPostgresImpl implements ConnectionManager {
    private static final Logger logger = Logger.getLogger(ConnectionManagerPostgresImpl.class);

    private static final ConnectionManagerPostgresImpl INSTANCE = new ConnectionManagerPostgresImpl();
    private Connection connection;

    public static synchronized ConnectionManagerPostgresImpl getInstance() {
        return INSTANCE;
    }

    private ConnectionManagerPostgresImpl() {
        try {
            Class.forName("org.postgresql.Driver");
            InputStream is = getClass().getResourceAsStream("/dbsettings.properties");
            Properties props = new Properties();
            props.load(is);
            is.close();
            String dbConnStr = props.getProperty("url");
            String username = props.getProperty("dbLogin");
            String password = props.getProperty("dbPassword");
            System.out.println(dbConnStr + " " + username + " " + password);
            connection = DriverManager.getConnection(dbConnStr, username, password);
        } catch (SQLException | ClassNotFoundException | IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            throw new SQLException("Can't get connection from pool");
        }

        return connection;
    }
}
