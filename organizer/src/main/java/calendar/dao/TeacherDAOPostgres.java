package calendar.dao;

import calendar.dao.exceptions.TeacherDAOException;
import classes.School;
import classes.SchoolType;
import classes.Subject;
import classes.Teacher;
import connectionmanager.ConnectionManager;
import connectionmanager.ConnectionManagerPostgresImpl;
import exceptions.UserNotFoundException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

public class TeacherDAOPostgres implements TeacherDAO {
    Logger logger = Logger.getLogger(TeacherDAOPostgres.class);

    @Override
    public Teacher getByUserId(int userId) throws TeacherDAOException {
        ConnectionManager connectionManager = ConnectionManagerPostgresImpl.getInstance();
        Teacher result = null;
        try {
            Connection connection = connectionManager.getConnection();
            try (PreparedStatement ps = connection.prepareStatement("select teach.id, teach.first_name, teach.last_name, teach.patronymic, teach.min_class_number, teach.max_class_number, " +
                    "   schools.id as sc_id, schools.name as sc_name, schools.region as sc_region, schools.city as sc_city, " +
                    "   school_types.id as sc_type_id, school_types.type_name as sc_type_name " +
                    "from teachers as teach " +
                    "   left join schools as schools on teach.school_id=schools.id " +
                    "   left join school_types as school_types on schools.school_type_id = school_types.id " +
                    "where teach.user_id=?");) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    result = new Teacher(rs.getInt(1),
                            userId,
                            rs.getString("last_name"),
                            rs.getString("first_name"),
                            rs.getString("patronymic"),
                            new School(rs.getInt("sc_id"),
                                    rs.getString("sc_name"),
                                    rs.getString("sc_region"),
                                    rs.getString("sc_city"),
                                    new SchoolType(rs.getInt("sc_type_id"), rs.getString("sc_type_name"))),
                            Collections.emptyList(),
                            rs.getInt("min_class_number"),
                            rs.getInt("max_class_number"),
                            null);

                    //Темы:
                    try (PreparedStatement psWork = connection.prepareStatement("select subjects.id, subjects.name from subjects join teacher_subjects on subjects.id=teacher_subjects.subject_id " +
                            " where teacher_subjects.teacher_id = ?");) {
                        psWork.setInt(1, result.getId());
                        rs = psWork.executeQuery();
                        while (rs.next())
                            result.getSubject().add(new Subject(rs.getInt("id"), rs.getString("name")));
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Ошибка при получении учителя : " + e.getLocalizedMessage(), e);
            throw new TeacherDAOException("Ошибка получения тестов. " + e.getLocalizedMessage(), e);
        }
        return result;
    }
}
