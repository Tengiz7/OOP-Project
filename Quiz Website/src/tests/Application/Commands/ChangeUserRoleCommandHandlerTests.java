package Application.Commands;

import junit.framework.TestCase;
import main.application.commands.user.ChangeUserRoleCommand.ChangeUserRoleCommandHandler;
import main.application.commands.user.ChangeUserRoleCommand.ChangeUserRoleCommandRequest;
import main.application.users.IUserRepository;
import main.domain.User;
import main.domain.enums.UserStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChangeUserRoleCommandHandlerTests extends TestCase {

    @Test
    public void testWhenEverythingIsFine_ShouldReturnResult() {
        ChangeUserRoleCommandRequest request = new ChangeUserRoleCommandRequest("username", UserStatus.ADMIN);

        IUserRepository userRepo = new IUserRepository() {
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
                return username.equals(request.getUsername());
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
        };

        ChangeUserRoleCommandHandler handler = new ChangeUserRoleCommandHandler(userRepo);
        assertNotNull(handler.handle(request));
    }

    @Test
    public void testWhenUserDoesntExist_ShouldThrowException() {
        ChangeUserRoleCommandRequest request = new ChangeUserRoleCommandRequest("username", UserStatus.ADMIN);

        IUserRepository userRepo = new IUserRepository() {
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
                return false;
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
        };

        ChangeUserRoleCommandHandler handler = new ChangeUserRoleCommandHandler(userRepo);
        assertThrows(RuntimeException.class, () -> handler.handle(request));
    }
}
