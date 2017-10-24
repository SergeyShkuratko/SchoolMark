package utils.dao;

import classes.Region;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUtilsRegion {

    public static Region getRegionByResultSet(ResultSet rs) throws SQLException {
        return new Region(
                rs.getInt("id"),
                rs.getInt("num"),
                rs.getString("name")
        );
    }

}
