package dao;

import classes.Region;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import exceptions.RegionDAOException;
import interfaces.dao.RegionDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegionDAOImpl implements RegionDAO {

    private static Logger logger = Logger.getLogger(RegionDAOImpl.class);
    private static ConnectionPool pool = TomcatConnectionPool.getInstance();

    private static final String GET_BY_ID = "SELECT * FROM region WHERE id = ?";

    @Override
    public Region getById(int id) throws RegionDAOException {
        Region result = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            List<Region> regions = regionFromResultSet(set);
            if (!regions.isEmpty()) {
                result = regions.get(0);
            }
        } catch (SQLException | RegionDAOException e) {
            logger.error(e.getMessage());
            logger.debug(e);
            throw new RegionDAOException(e);
        }
        return result;
    }

    @Override
    public Region insert(Region region) {
        return null;
    }

    @Override
    public List<Region> getAll() throws RegionDAOException {
        return null;
    }

    private List<Region> regionFromResultSet(ResultSet set) throws RegionDAOException {
        List<Region> result = new ArrayList<>();

        try {
            while (set.next()) {
                result.add(new Region(
                        set.getInt("id"),
                        set.getInt("num"),
                        set.getString("name")));
            }
        } catch (SQLException e) {
            throw new RegionDAOException(e);
        }
        return result;
    }
}
