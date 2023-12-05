package main.infrastructure;
import main.application.users.IUserRepository;
import main.domain.User;
import main.domain.enums.UserStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {

    private Connection connection;

    public UserRepository (Connection connection){
        this.connection = connection;
    }

    @Override
    public void addUser (User user){
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (username, password, userStatus) values (?, ?, ?);")){
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getUserStatus().toString());
            statement.execute();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public User userExists(String username) {
        try (PreparedStatement statement = connection.prepareStatement(
                "select * from Users q where q.username = ?")) {
            statement.setString(1, username);
            ResultSet set = statement.executeQuery();
            User result = null;
            while(set.next()) {
                result = new User(
                        set.getString("username"),
                        set.getString("password"),
                        UserStatus.valueOf(set.getString("userStatus").toUpperCase()));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeUser (String username){
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Users WHERE username = ?")){
            statement.setString(1, username);
            statement.execute();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public boolean isUsernameTaken (String username){
        boolean exists = false;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE username = ?")){
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                exists = true;
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    @Override
    public User getUser (String username, String password){
        User user = new User();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE username = ? AND password = ?") ){
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                user.setUsername(resultSet.getString("username"));
                user.setUserStatus(UserStatus.valueOf(resultSet.getString("userStatus")));
            } else {
                return null;
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void changeUserRole(String username, UserStatus newUserStatus) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE Users SET role = ? WHERE username = ?")){
            statement.setString(1,  newUserStatus.name());
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public List<User> search(String username){
        List<User> searchedUsers = new ArrayList<>();

        StringBuilder queryStatement = new StringBuilder("SELECT * FROM Users");
        queryStatement.append(" WHERE username LIKE '").append(username).append("%'");

        if(username.isEmpty()){
            queryStatement = new StringBuilder("SELECT * FROM Users");
        }

        try (PreparedStatement statement = connection.prepareStatement( queryStatement.toString())){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String resultUsername = resultSet.getString("username");
                String passwordHash = resultSet.getString("password");
                String roleName = resultSet.getString("userStatus");
                searchedUsers.add(new User(resultUsername, passwordHash, UserStatus.valueOf(roleName.toUpperCase())));
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return searchedUsers;
    }

    @Override
    public int getUsersNum() {
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM users") ){
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
}
