package main.infrastructure;

import main.application.users.IMessagesRepository;
import main.domain.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageRepository implements IMessagesRepository {
    private Connection connection;

    public MessageRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void sendMessage(String senderName, String receiverName, String message, LocalDateTime localDateTime) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Messages (senderUsername, receiverUsername, message, localDateTime) values (?,?,?,?)")){
            statement.setString(1, senderName);
            statement.setString(2, receiverName);
            statement.setString(3, message);
            statement.setString(4, String.valueOf(localDateTime));
            statement.executeUpdate();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public List<Message> getReceivedMessages(String receiverName) {
        List<Message> receivedMessages = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Messages WHERE receiverUsername = ?")){
            helper(receiverName, receivedMessages, statement);
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return receivedMessages;
    }

    @Override
    public List<Message> getSentMessages(String senderName) {
        List<Message> sentMessages = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Messages WHERE senderUsername = ?")){
            helper(senderName, sentMessages, statement);
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return sentMessages;
    }

    @Override
    public void deleteMessage(int messageID) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Messages WHERE id = ?")){
            statement.setString(1, Integer.toString(messageID));
            statement.executeUpdate();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    private void helper(String username, List<Message> sentMessages, PreparedStatement statement) throws SQLException {
        statement.setString(1,username);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Message message = new Message(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5).toLocalDateTime(), Integer.parseInt(resultSet.getString(1)));
            sentMessages.add(message);
        }
    }

    @Override
    public boolean messageExists(int messageID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Messages WHERE id = ?")){
            preparedStatement.setString(1, Integer.toString(messageID));
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
    public Message getMessage(int messageID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Messages WHERE id = ?")){
            preparedStatement.setString(1, Integer.toString(messageID));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return new Message(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5).toLocalDateTime(), Integer.parseInt(resultSet.getString(1)));
            }
            resultSet.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

}
