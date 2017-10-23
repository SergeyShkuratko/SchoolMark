package dao;

import connectionmanager.TomcatConnectionPool;
import dto.DTOFile;
import dto.DTOWork;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOStudentWork {

    public class DAOStudentWorkException extends SQLException {
        public DAOStudentWorkException(String reason) {
            super(reason);
        }
    }

    private final Logger logger = Logger.getLogger(this.getClass());

    public List<DTOWork> getWorksByStudentId(int student_id) throws DAOStudentWorkException {
        String sql = "SELECT w.id, w.status, t.start_date_time as date, templ.id templ_id, templ.topic, templ.description, " +
                        "sub.name as subject, res.mark, res.comment\n" +
                    "FROM works w \n" +
                    "\tJOIN (tests t JOIN (test_templates templ JOIN subjects sub ON templ.subject_id=sub.id)\n" +
                    "\tON (t.test_template_id=templ.id)) ON (w.test_id = t.id)\n" +
                    "\tJOIN verification_results res ON res.work_id = w.id";
        ArrayList<DTOWork> works = new ArrayList<>();
        try (Connection connection = TomcatConnectionPool.getInstance().getConnection())
        {
            ResultSet rs = DAOUtils.getResultSetExecuteQuery(connection, sql);
            while (rs.next()) {
                DTOWork work = new DTOWork(
                        rs.getInt("id"),
                        rs.getDate("date").toLocalDate(),
                        rs.getInt("templ_id"),
                        rs.getString("topic"),
                        rs.getString("description"),
                        rs.getString("subject"),
                        rs.getInt("mark"),
                        rs.getString("comment"),
                        convertStatus(rs.getString("status")));
                works.add(work);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOStudentWorkException(e.getMessage());
        }
        return works.size() != 0 ? works : null;
    }

    public DTOWork getWorkById(int id) throws DAOStudentWorkException {
        String sql = "SELECT w.id, w.status, t.start_date_time as date, templ.id templ_id, templ.topic, templ.description,sub.name as subject, res.mark, res.comment\n" +
                "                FROM works w \n" +
                "                JOIN (tests t JOIN (test_templates templ JOIN subjects sub ON templ.subject_id=sub.id)\n" +
                "                ON (t.test_template_id=templ.id)) ON (w.test_id = t.id)\n" +
                "                JOIN verification_results res ON res.work_id = w.id\n" +
                "WHERE w.id = " + id;
        DTOWork work=null;
        try (Connection connection = TomcatConnectionPool.getInstance().getConnection();
             ResultSet rs = DAOUtils.getResultSetExecuteQuery(connection, sql))
        {
                while (rs.next()) {
                    work = new DTOWork(
                            rs.getInt("id"),
                            rs.getDate("date").toLocalDate(),
                            rs.getInt("templ_id"),
                            rs.getString("topic"),
                            rs.getString("description"),
                            rs.getString("subject"),
                            rs.getInt("mark"),
                            rs.getString("comment"),
                            convertStatus(rs.getString("status")));
                }

        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOStudentWorkException(e.getMessage());
        }
        return work != null ? work : null;
    }

    private String convertStatus(String status) {
        switch (status) {
            case "new" : return "Новая";
            case "uploaded" : return "Загружена";
            case "in_progressappointed" : return "В обработке";
            case "on_verification" : return "На проверке";
            case "verified" : return "Проверена";
            case "confirmed" : return "Подтверждена";
            case "reverification" : return "На перепроверке";
        }
        return "";
    }

    public List<String> getQuestionListByTemplateId(int template_id) throws DAOStudentWorkException {
        String sql = "SELECT q.question\n" +
                "FROM questions q LEFT JOIN test_templates templ ON q.test_template_id = templ.id\n" +
                "WHERE templ.id = " + template_id;
        List<String> questions = new ArrayList<>();
        try(Connection connection = TomcatConnectionPool.getInstance().getConnection();
            ResultSet rs = DAOUtils.getResultSetExecuteQuery(connection, sql))
        {
            while (rs.next()) {
                questions.add(rs.getString("question"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOStudentWorkException(e.getMessage());
        }
        return questions.size() != 0 ? questions : null;
    }

    public List<DTOFile> getStudentFilesByWorkId(int work_id) throws DAOStudentWorkException {
        String sql = "SELECT id, file_url\n" +
                "FROM work_pages\n" +
                "WHERE work_id = " + work_id;
        return getFilesByQuery(sql);
    }

    public boolean addStudentFile(int work_id, String file) throws DAOStudentWorkException {
        String sql = "INSERT INTO work_pages (work_id, file_url) \n" +
                "VALUES (?, ?);";
        try (Connection connection = TomcatConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, work_id);
            preparedStatement.setString(2, file);
            return preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOStudentWorkException(e.getMessage());
        }
    }

    public boolean delStudentFile(int file_id) throws DAOStudentWorkException {
        String sql = "DELETE FROM work_pages\n" +
                "WHERE id = ?";
        try (Connection connection = TomcatConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, file_id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOStudentWorkException(e.getMessage());
        }
    }


    public List<DTOFile> getVerificationFilesByVerificationId(int verification_id) throws DAOStudentWorkException {
        String sql = "SELECT id, file_url\n" +
                "FROM verification_pages\n" +
                "WHERE verification_result_id = " + verification_id;
        return getFilesByQuery(sql);
    }

    private List<DTOFile> getFilesByQuery(String sql) throws DAOStudentWorkException {
        List<DTOFile> files = new ArrayList<>();
        try (Connection connection = TomcatConnectionPool.getInstance().getConnection();
             ResultSet rs = DAOUtils.getResultSetExecuteQuery(connection, sql))
        {
            while (rs.next()) {
                int id = rs.getInt("id");
                String filePath = rs.getString("file_url");
                DTOFile file = new DTOFile(id, filePath);
                files.add(file);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOStudentWorkException(e.getMessage());
        }
        return files.size() != 0 ? files : null;
    }

    public int setWorkStatus(int work_id, String status) throws DAOStudentWorkException {
        String sql = "UPDATE works" +
                " SET status = ?::work_status" +
                " WHERE id= ?";
        try (Connection connection = TomcatConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, work_id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOStudentWorkException(e.getMessage());
        }
    }


}
