package main.application.users;

import main.domain.User;
import main.domain.enums.UserStatus;

import java.util.List;

public interface IUserRepository {
    public void addUser (User user);
    public User userExists(String username);
    public void removeUser (String username);
    public boolean isUsernameTaken (String username);
    public User getUser (String username, String password);
    public void changeUserRole(String username, UserStatus newUserStatus);
    public List<User> search(String username);
    int getUsersNum();
}
