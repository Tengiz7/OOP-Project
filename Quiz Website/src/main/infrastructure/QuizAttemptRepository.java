package main.infrastructure;

import main.application.quiz.IQuizAttemptRepository;
import main.domain.QuizAttempt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizAttemptRepository implements IQuizAttemptRepository {
    private Connection connection;

    public QuizAttemptRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<QuizAttempt> getFriendsQuizAttempts(String username, int quizId) {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT qa.* FROM quizAttempts qa " +
                        "JOIN quizzes q ON q.id = qa.quizId " +
                        "WHERE qa.username IN (" +
                        "    SELECT username2 FROM friendships WHERE username1 = ?"+
                        ") AND qa.quizId = ?"+
                        "ORDER BY qa.id DESC LIMIT 10")) {
            statement.setString(1, username);
            statement.setInt(2, quizId);
            ResultSet set = statement.executeQuery();
            List<QuizAttempt> result = new ArrayList<>();
            while(set.next()) {
                result.add(new QuizAttempt(
                        set.getString("username"),
                        set.getInt("quizId"),
                        set.getInt("score")
                ));
            }
            return result;
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }

    public List<QuizAttempt> getQuizzesAttemptedByFriends(String username) {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT qa.* FROM quizAttempts qa " +
                        "JOIN quizzes q ON q.id = qa.quizId " +
                        "WHERE qa.username IN (" +
                        "    SELECT username2 FROM friendships WHERE username1 = ?"+
                        ")"+
                        "ORDER BY qa.id DESC LIMIT 10")) {
            statement.setString(1, username);
            ResultSet set = statement.executeQuery();
            List<QuizAttempt> result = new ArrayList<>();
            while(set.next()) {
                result.add(new QuizAttempt(
                    set.getString("username"),
                        set.getInt("quizId"),
                        set.getInt("score")
                ));
            }
            return result;
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }

    public void deleteHistory(int quizId){
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM quizAttempts where quizId = ?")){
            statement.setInt(1, quizId);
            statement.execute();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public int getQuizAttemptsNum() {
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM quizAttempts") ){
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
    public int getUniqueQuizAttemptsNum() {
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(DISTINCT quizId) FROM quizAttempts") ){
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
    public void addQuizAttempt(QuizAttempt quizAttempt) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO quizAttempts (username, quizId, score) VALUES (?, ?, ?)")){
            statement.setString(1, quizAttempt.getUsername());
            statement.setInt(2, quizAttempt.getQuizId());
            statement.setInt(3, quizAttempt.getScore());
            statement.execute();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public List<QuizAttempt> getQuizAttemptsByUser(String username, int quizId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM quizAttempts WHERE username = ? AND quizId = ?")){
            statement.setString(1, username);
            statement.setInt(2, quizId);
            ResultSet set = statement.executeQuery();
            List<QuizAttempt> result = new ArrayList<>();
            while(set.next()) {
                result.add(new QuizAttempt(
                        set.getString("username"),
                        set.getInt("quizId"),
                        set.getInt("score")
                ));
            }
            return result;
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<QuizAttempt> getTopQuizAttemptsByQuiz(int quizId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM quizAttempts WHERE quizId = ? ORDER BY score DESC LIMIT 10")){
            statement.setInt(1, quizId);
            ResultSet set = statement.executeQuery();
            List<QuizAttempt> result = new ArrayList<>();
            while(set.next()) {
                result.add(new QuizAttempt(
                        set.getString("username"),
                        set.getInt("quizId"),
                        set.getInt("score")
                ));
            }
            return result;
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public int getNumberOfTimesTaken(int quizId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM quizAttempts WHERE quizId = ?")){
            statement.setInt(1, quizId);
            ResultSet set = statement.executeQuery();
            if(set.next()) {
                return set.getInt(1);
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return -1;
    }

    @Override
    public int getNumberOfUniqueTimesTaken(int quizId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(DISTINCT username) FROM quizAttempts WHERE quizId = ?")){
            statement.setInt(1, quizId);
            ResultSet set = statement.executeQuery();
            if(set.next()) {
                return set.getInt(1);
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return -1;
    }

    @Override
    public int getMaxScore(int quizId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT MAX(score) FROM quizAttempts WHERE quizId = ?")){
            statement.setInt(1, quizId);
            ResultSet set = statement.executeQuery();
            if(set.next()) {
                return set.getInt(1);
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return -1;
    }

    @Override
    public double getAverageScore(int quizId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT AVG(score) FROM quizAttempts WHERE quizId = ?")){
            statement.setInt(1, quizId);
            ResultSet set = statement.executeQuery();
            if(set.next()) {
                return set.getDouble(1);
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return -1;
    }
}
