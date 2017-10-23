package core.utils.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOUtils {

    public static ResultSet getResultSetExecuteQueryByWhere(
            Connection connection, String baseSql)
            throws SQLException {
        try(Statement stmt = connection.createStatement()) {
            return stmt.executeQuery(baseSql);
        }
    }

    public static int getResultSetExecuteUpdateByWhere(
            Connection connection, String baseSql) throws SQLException {
        try(Statement stmt = connection.createStatement()) {
            return stmt.executeUpdate(baseSql);
        }
    }

}
