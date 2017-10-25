package dao;

import classes.City;
import classes.Region;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import exceptions.CityDAOException;
import exceptions.RegionDAOException;
import interfaces.dao.CityDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import utils.dao.DAOUtilsCity;
import utils.dao.DAOUtilsRegion;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static core.dao.constants.DAOConstants.NULL_POINTER_DB;

@Component
public class CityDAOImpl implements CityDAO {

    private static Logger logger = Logger.getLogger(CityDAOImpl.class);
    private static ConnectionPool pool = TomcatConnectionPool.getInstance();

    private static final String GET_BY_ID = "SELECT * FROM city WHERE id = ?";
    private static final String GET_BY_REGION = "SELECT * FROM city WHERE region_id = ?";
    private final String baseGetSql = "SELECT * FROM city ";
    private final String insertSql = "INSERT INTO city (region_id, name) VALUES (?, ?)";
    private final String deleteSql = "DELETE FROM city WHERE id = ?";
    private final String updateSql = "UPDATE city SET region_id = ?, name = ? WHERE id = ?";

    @Override
    public City getById(int id) throws CityDAOException {
        City result = null;
        try (Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            List<City> cities = cityListFromResultSet(set);
            if (!cities.isEmpty()) {
                result = cities.get(0);
            }
        } catch (SQLException | CityDAOException e) {
            logger.error(e.getMessage(), e);
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
            result = cityListFromResultSet(set);
        } catch (SQLException | CityDAOException e) {
            logger.error(e.getMessage(), e);
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
    public List<City> getAll() throws CityDAOException {
        return this.getMany("");
    }

    public List<City> getAllByRegion(Region region) throws CityDAOException {
        String where = region != null ? " WHERE region_id = " + region.getId() : "";
        return this.getMany(where);
    }

    private List<City> cityListFromResultSet(ResultSet set) throws CityDAOException {
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

    private City getOne(String where) throws CityDAOException {
        try(
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(baseGetSql + where)) {
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return DAOUtilsCity.getCityByResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new CityDAOException(e.getMessage());
        } catch (NullPointerException npe) {
            throw new CityDAOException(npe);
        }
    }

    private List<City> getMany(String where) throws CityDAOException {
        try(
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(baseGetSql + where)) {
            ResultSet rs = statement.executeQuery();
            ArrayList<City> cities = new ArrayList<>();
            while (rs.next()) {
                cities.add(DAOUtilsCity.getCityByResultSet(rs));
            }
            return cities.size() != 0 ? cities : null;
        } catch (SQLException e) {
            throw new CityDAOException(e.getMessage());
        } catch (NullPointerException npe) {
            throw new CityDAOException(npe);
        }
    }

    private void insertOne(City city, PreparedStatement preparedStatement)
            throws CityDAOException {
        try {
            preparedStatement.setInt(1, city.getRegionId());
            preparedStatement.setString(2, city.getName());
            preparedStatement.addBatch();
        } catch (SQLException e) {
            throw new CityDAOException(e.getMessage());
        } catch (NullPointerException npe) {
            throw new CityDAOException(npe.getMessage());
        }
    }

    private void checkInDBaseAndException(City city)
            throws CityDAOException {
        if(city.getId() != NULL_POINTER_DB)
            throw new CityDAOException (
                    "You can't insert object into db which is already there !!!");
    }

    private void checkDoesNotInDBaseAndException(City city)
            throws CityDAOException {
        if (city.getId() == NULL_POINTER_DB)
            throw new CityDAOException(
                    "You can't update object into db which have not there yet !!!");
    }

    @Override
    public int insertCity(City city) throws CityDAOException {
        this.checkInDBaseAndException(city);
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            this.insertOne(city, preparedStatement);
            preparedStatement.executeBatch();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) {
                return rs.getInt(1);
            }
            return NULL_POINTER_DB;
        } catch (SQLException e) {
            throw new CityDAOException(e.getMessage());
        }
    }

    @Override
    public int removeCity(City city) throws CityDAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(deleteSql)) {
            if(city.getId() == NULL_POINTER_DB) {
                throw new CityDAOException("City is not already exists !!!");
            }
            preparedStatement.setInt(1, city.getId());
            preparedStatement.addBatch();
            return preparedStatement.executeBatch().length;
        } catch (SQLException e) {
            throw new CityDAOException(
                    "Exception on delete row with id = " +
                            city.getId() + "(" + e.getMessage() + ")");
        } catch (NullPointerException npe) {
            throw new CityDAOException("input City has null value (" + npe.getMessage() + ")");
        }
    }

    @Override
    public int insertCities(List<City> cities)
            throws CityDAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(insertSql)) {
            for(City city : cities) {
                this.checkInDBaseAndException(city);
                this.insertOne(city, preparedStatement);
            }
            return preparedStatement.executeBatch().length;
        } catch (SQLException e) {
            throw new CityDAOException(e.getMessage());
        }
    }

    @Override
    public int updateCity(City city)
            throws CityDAOException {
        this.checkDoesNotInDBaseAndException(city);
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(updateSql)) {
            preparedStatement.setInt(1, city.getId());
            preparedStatement.setInt(2, city.getRegionId());
            preparedStatement.setString(3, city.getName());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new CityDAOException(e.getMessage());
        }
    }

}
