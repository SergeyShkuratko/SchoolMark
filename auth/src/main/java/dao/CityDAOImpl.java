package dao;

import classes.City;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import exceptions.CityDAOException;
import interfaces.dao.CityDAO;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAOImpl implements CityDAO {

    private static Logger logger = Logger.getLogger(CityDAOImpl.class);
    private static ConnectionPool pool = TomcatConnectionPool.getInstance();

    private static final String GET_BY_ID = "SELECT * FROM city WHERE id = ?";
    private static final String GET_BY_REGION = "SELECT * FROM city WHERE region_id = ?";

    @Override
    public City getById(int id) throws CityDAOException {
        City result = null;
        try (Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            List<City> cities = cityFromResultSet(set);
            if (!cities.isEmpty()) {
                result = cities.get(0);
            }
        } catch (SQLException | CityDAOException e) {
            logger.error(e.getMessage());
            logger.debug(e);
            throw new CityDAOException(e);
        }
        return result;
    }

    @Override
    public List<City> getByRegion(int id) throws CityDAOException {
        List<City> result = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_REGION)) {

            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            result = cityFromResultSet(set);
        } catch (SQLException | CityDAOException e) {
            logger.error(e.getMessage());
            logger.debug(e);
            throw new CityDAOException(e);
        } finally {
            if (result == null) {
                result = new ArrayList<>();
            }
        }
        return result;
    }

    @Override
    public List<City> getAllWithRegions() throws CityDAOException {
        return null;
    }

    @Override
    public City insert(City city) {
        return null;
    }

    private List<City> cityFromResultSet(ResultSet set) throws CityDAOException {
        List<City> result = new ArrayList<>();

        try {
            while (set.next()) {
                result.add(new City(
                        set.getInt("id"),
                        set.getInt("region_id"),
                        set.getString("name")));
            }
        } catch (SQLException e) {
            throw new CityDAOException(e);
        }
        return result;
    }
}
