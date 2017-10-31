package inno.dao;

import classes.dto.TeacherDTO;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import dao.SchoolDAOImpl;
import exceptions.SchoolTeacherDAOException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SchoolTeacherDAOImpl2 {
    private static Logger logger = Logger.getLogger(SchoolDAOImpl.class);

    private static final String GET_ALL_TEACHER = "SELECT * FROM teachers t" +
            " WHERE t.id<>?";

    private static ConnectionPool pool = TomcatConnectionPool.getInstance();


    public List<TeacherDTO> getAllTeacher(int organiserId) throws SchoolTeacherDAOException {
        List<TeacherDTO> teachers = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_TEACHER)) {
            statement.setInt(1, organiserId);
            ResultSet set = statement.executeQuery();
            teachers = setToTeachers(set);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new SchoolTeacherDAOException(e);
        }
        return teachers;
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
