package template.dao;


import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;

public class QuestionCriterionDAOImplementation {
    private static final Logger logger = Logger.getLogger(QuestionCriterionDAOImplementation.class);
    public static ConnectionPool connectionManager = TomcatConnectionPool.getInstance();

    public static int createQuestionCriterian(String criterion, int questionId) {
        int criterionId = 0;
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO question_verification_criterions(question_id, criterion) " +
                            "VALUES (?,?)",
                                Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, questionId);
            preparedStatement.setString(2, criterion);


            if (preparedStatement.executeUpdate() == 1){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                //получаем id только что добавленного критерия
                criterionId = resultSet.getInt(1);
                return criterionId;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return criterionId;
    }
}
