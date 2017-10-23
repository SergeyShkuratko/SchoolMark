package dao;

import classes.*;
import com.sun.org.apache.regexp.internal.RE;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import exceptions.CityDAOException;
import exceptions.RegisterUrlNotFoundException;
import exceptions.RoleDAOException;
import exceptions.SchoolDAOException;
import interfaces.dao.CityDAO;
import interfaces.dao.SchoolDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SchoolDAOImpl implements SchoolDAO {

    private static final Logger logger = Logger.getLogger(SchoolDAOImpl.class);
    private static final ConnectionPool pool = TomcatConnectionPool.getInstance();

    private static final String GET_BY_CITY = "SELECT * FROM schools s" +
            " LEFT JOIN school_types st ON st.id = s.school_type_id" +
            " WHERE city_id = ?";

    private static final String GET_ALL = "SELECT * FROM schools s" +
            " JOIN city c on s.city_id = c.id" +
            " JOIN region r on c.region_id = r.id" +
            " LEFT JOIN school_types st ON st.id = s.school_type_id";

    @Override
    public List<School> getAllSchoolsInCity(City city) throws SchoolDAOException {
        List<School> result = null;
        try (Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_BY_CITY)) {
            statement.setInt(1, city.getId());
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                result = setAndCityToSchools(set, city);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.debug(e);
            throw new SchoolDAOException();
        }
        return result;
    }

    @Override
    public List<School> getAll() throws SchoolDAOException {
        List<School> result = null;
        try (Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_ALL)) {
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                result = setToSchools(set);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.debug(e);
            throw new SchoolDAOException();
        }
        return result;
    }

    @Override
    public School getById(int id) {
        return new School(id, "Школа №1", "1", "1", new SchoolType(1, "1"));
    }

    @Override
    public List<SchoolClass> getAllClasses(School school) {
        List<SchoolClass> classes = new ArrayList<>();
        classes.add(new SchoolClass(1, 11, "11А", school));
        classes.add(new SchoolClass(2, 11, "11Б", school));
        classes.add(new SchoolClass(3, 11, "11В", school));

        return classes;
    }

    private List<School> setAndCityToSchools(ResultSet set, City city) throws SchoolDAOException {
        List<School> result = new ArrayList<>();
        try {
            while (set.next()) {
                result.add(new School(
                        set.getInt("s.id"),
                        set.getString("s.name"),
                        city,
                        new SchoolType(
                                set.getInt("st.id"),
                                set.getString("st.type_name"))
                        ));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.debug(e);
            throw new SchoolDAOException();
        }
        return result;
    }

    private List<School> setToSchools(ResultSet set) throws SchoolDAOException {
        List<School> result = new ArrayList<>();
        Map<Integer, City> cityMap = new HashMap<>();
        Map<Integer, Region> regionMap = new HashMap<>();
        try {
            while (set.next()) {
                Region region = getRegion(regionMap, set);
                City city = getCity(cityMap, set, region);

                result.add(new School(
                        set.getInt("s.id"),
                        set.getString("s.name"),
                        city,
                        new SchoolType(
                                set.getInt("st.id"),
                                set.getString("st.type_name"))
                ));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.debug(e);
            throw new SchoolDAOException();
        }
        return result;
    }

    private Region getRegion(Map<Integer, Region> regionMap, ResultSet set) throws SQLException {
        //TODO подумать про логирование
        Region region;
        int regionId = set.getInt("r.id");
        if (regionMap.containsKey(regionId)) {
            region = regionMap.get(regionId);
        } else {
            region = new Region(regionId, set.getString("r.name"));
            regionMap.put(regionId, region);
        }
        return region;
    }

    private City getCity(Map<Integer, City> cityMap, ResultSet set, Region region) throws SQLException {
        //TODO подумать про логирование
        City city;
        int cityId = set.getInt("c.id");
        if (cityMap.containsKey(cityId)) {
            city = cityMap.get(cityId);
        } else {
            city = new City(cityId,
                    new Region(set.getInt("r.id"),
                            set.getString("r.name")),
                    set.getString("c.name"));
            cityMap.put(cityId, city);
        }
        return city;
    }
}
