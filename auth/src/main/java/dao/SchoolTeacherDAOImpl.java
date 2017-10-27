package dao;

import classes.Teacher;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import classes.dto.TeacherDTO;
import exceptions.SchoolTeacherDAOException;
import interfaces.dao.SchoolTeacherDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SchoolTeacherDAOImpl implements SchoolTeacherDAO {
    private static Logger logger = Logger.getLogger(SchoolDAOImpl.class);

    private static final String GET_BY_SCHOOL = "SELECT * FROM teachers t" +
            " LEFT JOIN users u ON u.id = t.user_id" +
            " WHERE school_id = ?";

    private static ConnectionPool pool = TomcatConnectionPool.getInstance();

    @Override
    public List<TeacherDTO> getTeacherBySchool(int id) throws SchoolTeacherDAOException {
        List<TeacherDTO> teachers = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_SCHOOL)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            teachers = setToTeachers(set);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new SchoolTeacherDAOException(e);
        }
        return teachers;
    }

    @Override
    public boolean addTeacher(Teacher teacher) throws SchoolTeacherDAOException {
        return false;
    }

    private List<TeacherDTO> setToTeachers(ResultSet set) throws SchoolTeacherDAOException {
        List<TeacherDTO> teachers = new ArrayList<>();
        try {
            while (set.next()) {
                teachers.add(new TeacherDTO(
                        set.getInt("id"),
                        set.getInt("user_id"),
                        set.getString("last_name"),
                        set.getString("first_name"),
                        set.getString("patronymic"),
                        set.getInt("school_id")
                        ));
            }
        } catch (SQLException e) {
            throw new SchoolTeacherDAOException(e);
        }
        return teachers;
    }
}
