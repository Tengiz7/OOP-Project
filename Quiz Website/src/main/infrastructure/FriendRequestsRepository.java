package main.infrastructure;

import main.application.users.IFriendRequestRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendRequestsRepository implements IFriendRequestRepository {

    private Connection connection;


    public FriendRequestsRepository(Connection connection) {
        this.connection = connection;
    }

    public void addFriendRequest (String senderName, String receiverName, String message){
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO FriendRequests (Sender_Username, Receiver_Username, Message) values (?,?,?)")){
            statement.setString(1, senderName);
            statement.setString(2, receiverName);
            statement.setString(3, message);
            statement.execute();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }
    public void removeFriendRequest (String senderName, String receiverName){
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM FriendRequests WHERE Sender_Username = ? AND Receiver_Username = ?")){
            statement.setString(1, senderName);
            statement.setString(2, receiverName);
            statement.execute();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public boolean friendRequestExists (String senderName, String receiverName){
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM FriendRequests WHERE Sender_Username = ? AND Receiver_Username = ?") ){
            statement.setString(1, senderName);
            statement.setString(2, receiverName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return true;
            }
            resultSet.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public List<String> getSentFriendRequests(String senderName){
        List<String> sentFriendRequests = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT Receiver_Username FROM FriendRequests WHERE Sender_Username = ?")){
            statement.setString(1,senderName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                sentFriendRequests.add(resultSet.getString(1));
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return sentFriendRequests;
    }

    public List<String> getReceivedFriendRequests(String receiverName){
        List<String> receivedFriendRequests = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT Sender_Username FROM FriendRequests WHERE Receiver_Username = ?")){
            statement.setString(1,receiverName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                receivedFriendRequests.add(resultSet.getString(1));
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return receivedFriendRequests;
    }
}
