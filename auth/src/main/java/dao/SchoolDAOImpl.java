package dao;

import classes.*;
import classes.dto.SchoolDTO;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import exceptions.SchoolDAOException;
import interfaces.dao.SchoolDAO;
import interfaces.dao.SchoolsDAO;
import org.apache.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.*;

public class SchoolDAOImpl implements SchoolsDAO {

    private static Logger logger = Logger.getLogger(SchoolDAOImpl.class);
    private static ConnectionPool pool = TomcatConnectionPool.getInstance();

    private static final String GET_BY_ID = "SELECT s.id as s_id, r.id as r_id, c.id as c_id," +
            " r.name as r_name, c.name as c_name, s.name as s_name," +
            " st.id as type_id, st.type_name" +
            " FROM schools as s" +
            " JOIN city as c on s.city_id = c.id" +
            " JOIN region as r on c.region_id = r.id" +
            " JOIN school_types as st ON st.id = s.school_type_id" +
            " WHERE s.id = ?";

    private static final String GET_BY_CITY = "SELECT * FROM schools s" +
            " LEFT JOIN school_types st ON st.id = s.school_type_id" +
            " WHERE city_id = ?";

    private static final String GET_CLASSES_BY_SCHOOL = "SELECT * FROM school_classes c" +
            " WHERE school_id = ?";

    private static final String GET_ALL =
            "SELECT s.id as s_id, r.id as r_id, c.id as c_id," +
                    " r.name as r_name, c.name as c_name, s.name as s_name," +
                    " st.id as st_id, st.type_name, st.id as type_id" +
                    " FROM schools as s" +
                    " JOIN city as c on s.city_id = c.id" +
                    " JOIN region as r on c.region_id = r.id" +
                    " JOIN school_types as st ON st.id = s.school_type_id";

    private static final String INSERT =
            "INSERT INTO SCHOOLS(ID, NAME, SCHOOL_TYPE_ID, CITY_ID, CITY, REGION) VALUES( DEFAULT, ?,?,?,?,?) ON CONFLICT (NAME, SCHOOL_TYPE_ID, CITY_ID) DO NOTHING";

    private static final String UPDATE =
            "UPDATE SCHOOLS SET NAME=?, SCHOOL_TYPE_ID=?, CITY_ID=?, CITY=?, REGION=? WHERE ID = ? ";
    private static final String TEST_UPDATE =
            "SELECT COUNT(ID) FROM SCHOOLS WHERE NAME=? AND SCHOOL_TYPE_ID=? AND CITY_ID=? AND ID <> ?";

    private static final String GET_ALL_SCHOOL_TYPES =
            "SELECT * from school_types";

    @Override
    public SchoolDTO getById(int id) throws SchoolDAOException {
        SchoolDTO school = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            List<SchoolDTO> schools = getSchoolsFromSet(set);
            if (schools.isEmpty()) {
                throw new SchoolDAOException();
            }
            school = schools.get(0);
        } catch (SQLException | SchoolDAOException e) {
            logger.error(e.getMessage(), e);
            throw new SchoolDAOException();
        }
        return school;
    }

    @Override
    public List<SchoolDTO> getAllSchoolsInCity(int id) throws SchoolDAOException {
        List<SchoolDTO> schools = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_CITY)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            schools = getSchoolsFromSet(set);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new SchoolDAOException();
        }
        return schools;
    }

    @Override
    public List<SchoolDTO> getAll() throws SchoolDAOException {
        List<SchoolDTO> schools = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL)) {
            ResultSet set = statement.executeQuery();
            schools = getSchoolsFromSet(set);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new SchoolDAOException();
        }
        return schools;
    }

    @Override
    public List<SchoolClass> getAllClasses(int id) throws SchoolDAOException {
        List<SchoolClass> classes = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_CLASSES_BY_SCHOOL)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            classes = getClassesFromSet(set);
        } catch (SQLException | SchoolDAOException e) {
            throw new SchoolDAOException(e);
        }

        return classes;
    }

    private List<SchoolClass> getClassesFromSet(ResultSet set) throws SchoolDAOException {
        List<SchoolClass> classes = new ArrayList<>();
        try {
            while (set.next()) {
                classes.add(new SchoolClass(
                        set.getInt("id"),
                        set.getInt("number"),
                        set.getString("name")));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new SchoolDAOException(e);
        }
        return classes;
    }

    private List<SchoolDTO> getSchoolsFromSet(ResultSet set) throws SchoolDAOException {
        List<SchoolDTO> schools = new ArrayList<>();
        try {
            while (set.next()) {
                schools.add(new SchoolDTO(
                        set.getInt("s_id"),
                        set.getString("s_name"),
                        set.getInt("r_id"),
                        set.getInt("c_id"),
                        set.getString("r_name"),
                        set.getString("c_name"),
                        set.getString("type_name"),
                        set.getInt("type_id")));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new SchoolDAOException(e);
        }
        return schools;
    }

    @Override
    public boolean insertSchool(SchoolDTO school) throws SchoolDAOException {

        boolean result = false;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, school.getName());
            statement.setInt(2, school.getSchoolTypeId());
            statement.setInt(3, school.getCityId());
            statement.setString(4, school.getCityName());
            statement.setString(5, school.getRegionName());
            if (statement.executeUpdate() == 1) {
                result = true;
                ResultSet rs = statement.getGeneratedKeys();
                rs.next();
                school.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new SchoolDAOException(e);
        }
        return result;
    }

    @Override
    public boolean updateSchool(SchoolDTO school) throws SchoolDAOException {
        boolean result = false;
        try (Connection connection = pool.getConnection()) {
            boolean canInsert = true;
            try(PreparedStatement statement = connection.prepareStatement(TEST_UPDATE)){
                statement.setString(1, school.getName());
                statement.setInt(2, school.getSchoolTypeId());
                statement.setInt(3, school.getCityId());
                statement.setInt(4, school.getId());
                ResultSet rs = statement.executeQuery();
                if(rs.next() && rs.getInt(1)>0)
                    canInsert = false;
            }
            if(canInsert) {
                try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
                    statement.setString(1, school.getName());
                    statement.setInt(2, school.getSchoolTypeId());
                    statement.setInt(3, school.getCityId());
                    statement.setString(4, school.getCityName());
                    statement.setString(5, school.getRegionName());
                    statement.setInt(6, school.getId());
                    result = statement.executeUpdate() == 1;
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new SchoolDAOException(e);
        }
        return result;
    }

    @Override
    public List<SchoolType> getAllSchoolTypes() throws SchoolDAOException {
        List<SchoolType> result;
        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(GET_ALL_SCHOOL_TYPES);
            result = new ArrayList<>();
            while (rs.next()) {
                SchoolType st = new SchoolType(rs.getInt("id"),
                        rs.getString("type_name"));
                result.add(st);
            }
        } catch (SQLException e) {
            result = Collections.emptyList();
            logger.error(e);
            throw new SchoolDAOException(e);
        }
        return result;
    }
}
