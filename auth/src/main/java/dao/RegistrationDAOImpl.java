package dao;

import classes.Role;
import classes.dto.RegistrationUrlDTO;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import interfaces.dao.RegistrationUrlDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAOImpl implements RegistrationUrlDAO {
    private static Logger logger = Logger.getLogger(SchoolDAOImpl.class);
    private static ConnectionPool pool = TomcatConnectionPool.getInstance();


    @Override
    public String getUrl(Role role, int schoolId, int classId) {
        String sql = "SELECT URL FROM REGISTRATION_URL WHERE ROLE = ?::role AND SCHOOL_ID = ? AND CLASS_ID = ?";
        String result = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, role.name());
            ps.setInt(2, schoolId);
            ps.setInt(3, classId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) result = rs.getString(1);
        } catch (SQLException e) {
            logger.error(e);
            result = null;
        }
        return result;
    }

    @Override
    public boolean setUrl(String newUrl, String oldUrl, Role role, int schoolId, int classId) {
        String sqlUpdate = "UPDATE REGISTRATION_URL Set URL = ?, ROLE = ?::role, SCHOOL_ID = ?, CLASS_ID = ? WHEN URL=?";
        String sqlInsert = "INSERT INTO REGISTRATION_URL(URL, ROLE, SCHOOL_ID, CLASS_ID) VALUES(?,?::role, ?, ?)";
        boolean result = true;
        try (Connection connection = pool.getConnection()) {
            if (oldUrl == null) {
                try (PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
                    ps.setString(1, newUrl);
                    ps.setString(2, role.name());
                    ps.setInt(3, schoolId);
                    ps.setInt(4, classId);
                    ps.executeUpdate();
                }
            } else {
                try (PreparedStatement ps = connection.prepareStatement(sqlUpdate)) {
                    ps.setString(1, newUrl);
                    ps.setString(2, role.name());
                    ps.setInt(3, schoolId);
                    ps.setInt(4, classId);
                    ps.setString(5, oldUrl);
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            result = false;
        }
        return result;
    }

    @Override
    public RegistrationUrlDTO getUrlDTO(String url) {
        String sql = "SELECT URL, ROLE, SCHOOL_ID, CLASS_ID FROM REGISTRATION_URL WHERE URL = ?";
        RegistrationUrlDTO result = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, url);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = new RegistrationUrlDTO(
                        rs.getString("URL"),
                        rs.getString("ROLE"),
                        rs.getInt("SCHOOL_ID"),
                        rs.getInt("CLASS_ID"));
            }
        } catch (SQLException e) {
            logger.error(e);
            result = null;
        }
        return result;
    }

    @Override
    public List<RegistrationUrlDTO> getAllUrlDtoBySchool(int schoolId) {
        String sql = "SELECT URL, ROLE, SCHOOL_ID, CLASS_ID FROM REGISTRATION_URL WHERE SCHOOL_ID = ?";
        List<RegistrationUrlDTO> result = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, schoolId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                RegistrationUrlDTO dto = new RegistrationUrlDTO(
                        rs.getString("URL"),
                        rs.getString("ROLE"),
                        rs.getInt("SCHOOL_ID"),
                        rs.getInt("CLASS_ID"));
                result.add(dto);
            }
        } catch (SQLException e) {
            logger.error(e);
            result = null;
        }
        return result;
    }
}
