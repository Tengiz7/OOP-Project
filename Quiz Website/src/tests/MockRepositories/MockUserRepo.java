package MockRepositories;

import main.application.users.IUserRepository;
import main.domain.User;
import main.domain.enums.UserStatus;

import java.util.List;

public class MockUserRepo implements IUserRepository {
    @Override
    public void addUser(User user) {
    }

    @Override
    public User userExists(String username) {
        return null;
    }

    @Override
    public void removeUser(String username) {
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return true;
    }

    @Override
    public User getUser(String username, String password) {
        return null;
    }

    @Override
    public void changeUserRole(String username, UserStatus newUserStatus) {
    }

    @Override
    public List<User> search(String username) {
        return null;
    }

    @Override
    public int getUsersNum() {
        return 0;
    }
}
