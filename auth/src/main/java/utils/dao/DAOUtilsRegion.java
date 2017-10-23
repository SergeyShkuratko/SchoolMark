package servlets.utils.dao;

import classes.Region;
import core.utils.dao.DAOUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUtilsRegion extends DAOUtils {

    public static Region getRegionByResultSet(ResultSet rs) throws SQLException {
        return new Region(
                rs.getInt("id"),
                rs.getInt("num"),
                rs.getString("name")
        );
    }

}
