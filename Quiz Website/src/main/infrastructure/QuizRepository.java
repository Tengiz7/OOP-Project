package main.infrastructure;

import main.application.quiz.IQuizRepository;
import main.domain.Question;
import main.domain.Quiz;
import main.domain.User;
import main.domain.enums.QuestionType;
import main.domain.enums.UserStatus;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuizRepository implements IQuizRepository {

    private Connection connection;

    public QuizRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Quiz> getNewQuizes() {
        try (PreparedStatement statement = connection.prepareStatement(
                "select * from Quizzes q order by id desc")) {
            ResultSet set = statement.executeQuery();
            List<Quiz> result = new ArrayList<>();
            while(set.next()) {
                result.add(new Quiz(
                        set.getInt("id"),
                        set.getString("title"),
                        set.getString("description"),
                        set.getTimestamp("createdAt").toLocalDateTime(),
                        set.getString("creatorId")
                ));
            }
            return result;
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }

    public List<Quiz> getQuizesByCreatorId(String username) {
        try (PreparedStatement statement = connection.prepareStatement(
                "select * from Quizzes q where creatorId = ? order by createdAt desc")) {
            statement.setString(1, username);
            ResultSet set = statement.executeQuery();
            List<Quiz> result = new ArrayList<>();
            while(set.next()) {
                result.add(new Quiz(
                        set.getInt("id"),
                        set.getString("title"),
                        set.getString("description"),
                        set.getTimestamp("createdAt").toLocalDateTime(),
                        set.getString("creatorId")
                ));
            }
            return result;
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }

    public List<Quiz> getQuizesByFriends(String username) {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT q.* FROM quizzes q " +
                        "JOIN users u ON u.username = q.creatorId " +
                        "WHERE u.username IN (" +
                        "    SELECT username2 FROM Friendships WHERE username1 = ?" +
                        ")"
                        )) {
            statement.setString(1, username);
            ResultSet set = statement.executeQuery();
            List<Quiz> result = new ArrayList<>();
            while(set.next()) {
                result.add(new Quiz(
                        set.getInt("id"),
                        set.getString("title"),
                        set.getString("description"),
                        set.getTimestamp("createdAt").toLocalDateTime(),
                        set.getString("creatorId")
                ));
            }
            return result;
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Quiz> searchQuizes(String title) {
        List<Quiz> searchedQuizzes= new ArrayList<>();

        StringBuilder queryStatement = new StringBuilder("SELECT * FROM Quizzes");
        queryStatement.append(" WHERE title LIKE '").append(title).append("%'");

        if(title.isEmpty()){
            queryStatement = new StringBuilder("SELECT * FROM Quizzes");
        }

        try (PreparedStatement statement = connection.prepareStatement( queryStatement.toString())){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                searchedQuizzes.add(new Quiz(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getTimestamp("createdAt").toLocalDateTime(),
                        resultSet.getString("creatorId")
                ));
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return searchedQuizzes;
    }

    public List<Quiz> getPopularQuizes() {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT q.*, COUNT(qa.id) AS popularity " +
                        "FROM quizzes q " +
                        "LEFT JOIN quizAttempts qa ON q.id = qa.quizId " +
                        "GROUP BY q.id " +
                        "ORDER BY popularity DESC LIMIT 4")) {
            ResultSet set = statement.executeQuery();
            List<Quiz> result = new ArrayList<>();
            while(set.next()) {
                result.add(new Quiz(
                        set.getInt("id"),
                        set.getString("title"),
                        set.getString("description"),
                        set.getTimestamp("createdAt").toLocalDateTime(),
                        set.getString("creatorId")
                ));
            }
            return result;
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }

    public List<Quiz> getQuizesByUserAttempts(String username) {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT q.* FROM quizzes q " +
                        "JOIN quizAttempts qa ON q.id = qa.quizId " +
                        "WHERE qa.username = ? " +
                        "GROUP BY q.id " +
                        "ORDER BY q.createdAt DESC")) {
            statement.setString(1, username);
            ResultSet set = statement.executeQuery();
            List<Quiz> result = new ArrayList<>();
            while(set.next()) {
                result.add(new Quiz(
                        set.getInt("id"),
                        set.getString("title"),
                        set.getString("description"),
                        set.getTimestamp("createdAt").toLocalDateTime(),
                        set.getString("creatorId")
                ));
            }
            return result;
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }

    public Quiz getQuizById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(
                "select * from Quizzes q join questions quest on q.id = quest.quizId where q.id = " + id)) {
            ResultSet set = statement.executeQuery();
            Quiz result = null;
            while(set.next()) {
                if(result == null) {
                    result = new Quiz(
                            set.getInt("id"),
                            set.getString("title"),
                            set.getString("description"),
                            set.getTimestamp("createdAt").toLocalDateTime(),
                            set.getString("creatorId"));
                }
                String questionType = set.getString("questionType");
                QuestionType type = questionType.equals("BLANK") ? QuestionType.BLANK :
                        questionType.equals("QUESTION_RESPONSE") ? QuestionType.QUESTION_RESPONSE :
                                QuestionType.MULTIPLE_CHOICE;
                result.addQuestion(new Question(
                        set.getInt(6),
                        id,
                        set.getString("question"),
                        set.getString("answer"),
                        set.getString("imageSource"),
                        type)
                );
            }
            return result;
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }

    public void deleteQuiz (int id){
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Quizzes q where q.id = " + id)){
            statement.execute();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public int getQuizesNum() {
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM quizzes") ){
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int addQuiz(Quiz quiz) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Quizzes (title, description, createdAt, creatorId) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, quiz.getTitle());
            statement.setString(2, quiz.getDescription());
            statement.setTimestamp(3, Timestamp.valueOf(quiz.getCreatedAt()));
            statement.setString(4, quiz.getCreatorId());
            statement.executeUpdate();
            ResultSet set = statement.getGeneratedKeys();
            if(set.next()) {
                return set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
