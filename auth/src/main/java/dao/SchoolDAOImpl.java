package dao;

import classes.SchoolClass;
import classes.dto.SchoolDTO;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import exceptions.SchoolDAOException;
import interfaces.dao.SchoolsDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SchoolDAOImpl implements SchoolsDAO {

    private static Logger logger = Logger.getLogger(SchoolDAOImpl.class);
    private static ConnectionPool pool = TomcatConnectionPool.getInstance();

    private static final String GET_BY_ID = "SELECT s.id as s_id, r.id as r_id, c.id as c_id," +
            " r.name as r_name, c.name as c_name, s.name as s_name," +
            " st.id as st_id, st.type_name" +
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
            " st.id as st_id, st.type_name" +
            " FROM schools as s" +
            " JOIN city as c on s.city_id = c.id" +
            " JOIN region as r on c.region_id = r.id" +
            " JOIN school_types as st ON st.id = s.school_type_id";

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
                        set.getString("type_name")));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new SchoolDAOException(e);
        }
        return schools;
    }

}
