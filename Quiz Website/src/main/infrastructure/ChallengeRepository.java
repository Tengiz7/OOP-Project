package main.infrastructure;

import main.application.users.IChallengeRepository;
import main.domain.Challenge;
import main.domain.Message;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ChallengeRepository implements IChallengeRepository {

    private Connection connection;

    public ChallengeRepository(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void addChallenge(String senderName, String receiverName, int quizID, String message, LocalDateTime localDateTime) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Challenges (senderUsername, receiverUsername, message, localDateTime, quizId) values (?,?,?,?,?)")){
            statement.setString(1, senderName);
            statement.setString(2, receiverName);
            statement.setString(3, message);
            statement.setString(4, String.valueOf(localDateTime));
            statement.setString(5, Integer.toString(quizID));
            statement.execute();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public List<Challenge> getReceivedChallenges(String receiverName) {
        List<Challenge> receivedChallenges = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Challenges WHERE receiverUsername = ?")){
            statement.setString(1, receiverName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Challenge challenge = new Challenge(resultSet.getString(2),
                        resultSet.getString(3),
                        Integer.parseInt(resultSet.getString(4)),
                        resultSet.getString(5),
                        resultSet.getTimestamp(6).toLocalDateTime(),
                        Integer.parseInt(resultSet.getString(1))
                        );
                receivedChallenges.add(challenge);
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return receivedChallenges;
    }

    @Override
    public void deleteChallenge(int quizID) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Challenges WHERE id = ?")){
            statement.setString(1, Integer.toString(quizID));
            statement.execute();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public boolean challengeExists(int quizID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Challenges WHERE id = ?")){
            preparedStatement.setString(1, Integer.toString(quizID));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }
            resultSet.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public Challenge getChallenge(int challengeId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM challenges where id = " + challengeId)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Challenge(resultSet.getString(2),
                        resultSet.getString(3),
                        Integer.parseInt(resultSet.getString(4)),
                        resultSet.getString(5),
                        resultSet.getTimestamp(6).toLocalDateTime(),
                        Integer.parseInt(resultSet.getString(1))
                );
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
