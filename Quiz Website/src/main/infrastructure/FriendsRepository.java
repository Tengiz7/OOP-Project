package main.infrastructure;

import main.application.users.IFriendsRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FriendsRepository implements IFriendsRepository {
    private Connection connection;

    public FriendsRepository(Connection connection) {
        this.connection = connection;
    }

    public List<String> getFriendships(String user){
        List<String> friendships = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT username2 FROM Friendships WHERE username1 = ?")){
            statement.setString(1, user);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                friendships.add(resultSet.getString(1));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return friendships;
    }
    
    public void addFriend (String user, String futureFriend, LocalDateTime localDateTime){
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Friendships (username1, username2, date) values (?,?,?)")) {
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, futureFriend);
            preparedStatement.setString(3, String.valueOf(localDateTime));
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void removeFriendship (String user, String oldFriend){
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Friendships WHERE username1 = ? AND username2 = ?")) {
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, oldFriend);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public boolean friendshipExists (String user1, String user2){
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Friendships WHERE username1 = ? AND username2 = ?")){
            preparedStatement.setString(1, user1);
            preparedStatement.setString(2, user2);
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

}
