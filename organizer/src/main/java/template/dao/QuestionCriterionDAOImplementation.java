package template.dao;


import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;

import java.sql.*;

/**
 * Created by nkm on 16.10.2017.
 */
public class QuestionCriterionDAOImplementation {
    public static ConnectionPool connectionManager = TomcatConnectionPool.getInstance();
    public static int createQuestionCriterian(String criterion, int questionId) {
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
                int criterionId = resultSet.getInt(1);
                return criterionId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
