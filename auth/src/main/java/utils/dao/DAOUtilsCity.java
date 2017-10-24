package utils.dao;

import classes.City;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUtilsCity {
    public static City getCityByResultSet(ResultSet rs) throws SQLException {
        return new City(
                rs.getInt("id"),
                rs.getInt("region_id"),
                rs.getString("name")
        );
    }
}
