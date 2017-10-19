package connectionmanager;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TomcatConnectionPool implements ConnectionPool {

    private static class Holder {
        private static final TomcatConnectionPool INSTANCE = new TomcatConnectionPool();
    }

    private static DataSource dataSource;
    private final Logger logger = Logger.getLogger(TomcatConnectionPool.class);

    private TomcatConnectionPool() {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            dataSource = (DataSource) envCtx.lookup("jdbc/SM");
        } catch (NamingException e) {
            logger.error(e.getMessage());
            logger.getLogger(TomcatConnectionPool.class).debug(e.fillInStackTrace());
        }
    }

    public static TomcatConnectionPool getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
