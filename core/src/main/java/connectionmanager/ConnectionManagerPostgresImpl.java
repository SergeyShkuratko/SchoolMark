package connectionmanager;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionManagerPostgresImpl implements ConnectionManager {
    private static final Logger logger = Logger.getLogger(ConnectionManagerPostgresImpl.class);

    private static final ConnectionManagerPostgresImpl INSTANCE = new ConnectionManagerPostgresImpl();
    private List<Connection> freeConnection = new ArrayList<>();
    private List<Connection> useConnection = new ArrayList<>();
    private String dbUserName;
    private String dbPassword;
    private String dbConnString;
    private int maxConnection = 0;

    public static ConnectionManagerPostgresImpl getInstance() {
        return INSTANCE;
    }

    private ConnectionManagerPostgresImpl() {
        try (InputStream is = getClass().getResourceAsStream("/dbsettings.properties")) {
            Class.forName("org.postgresql.Driver");
            Properties props = new Properties();
            props.load(is);
            dbConnString = props.getProperty("url");
            dbUserName = props.getProperty("dbLogin");
            dbPassword = props.getProperty("dbPassword");
            maxConnection = Integer.parseInt(props.getProperty("maxConnection"));
        } catch (ClassNotFoundException | IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
    @Override
    public synchronized Connection getConnection() throws SQLException{
        if(maxConnection == 0) throw new SQLException("Соединение не установленно!!");
        try {
            while (useConnection.size()>= maxConnection) wait();
        } catch (InterruptedException e) {
            logger.error("Прерывание ожидания получения соединения! "+e.getMessage(), e);
            throw new SQLException("Соединение не установленно!!");
        }
        Connection result = null;
        if(!freeConnection.isEmpty())
            result = freeConnection.remove(freeConnection.size()-1);
        else
            result = DriverManager.getConnection(dbConnString, dbUserName, dbPassword);
        useConnection.add(result);
        return result;
    }
    @Override
    public synchronized void putBackConnection(Connection connection) {
        int index = useConnection.indexOf(connection);
        if(index==-1) return;
        freeConnection.add(useConnection.remove(index));
        notify();
    }

    @Override
    protected void finalize() throws Throwable {
        for (Connection connection : freeConnection) {
            try{
                connection.close();
            }catch (SQLException e){
                logger.error("ошибка закрытия соединения "+e.getMessage(),e);
            }
        }
        for (Connection connection : useConnection) {
            try{
                connection.close();
            }catch (SQLException e){
                logger.error("ошибка закрытия соединения "+e.getMessage(),e);
            }
        }
    }
}
