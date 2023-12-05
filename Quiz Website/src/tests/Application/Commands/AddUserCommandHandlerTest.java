package Application.Commands;

import MockRepositories.MockUserRepo;
import junit.framework.TestCase;
import main.application.commands.user.AddUserComand.AddUserCommandHandler;
import main.application.commands.user.AddUserComand.AddUserCommandRequest;
import main.application.users.Hasher;
import main.application.users.IUserRepository;
import main.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;

public class AddUserCommandHandlerTest extends TestCase {

    @Test
    public void testWhenEverythingIsFine_ShouldReturnResult() {
        AddUserCommandRequest request = new AddUserCommandRequest();
        request.setUsername("tezi");
        request.setPassword("password");

        IUserRepository userRepository = new MockUserRepo(){
            @Override
            public void addUser(User user) {
                if(!(user.getUsername().equals(request.getUsername()) || user.getPassword().equals(Hasher.hashPassword(request.getPassword())))){
                    throw new RuntimeException("Invalid Data");
                }
            }

            @Override
            public boolean isUsernameTaken(String username) {
                return false;
            }
        };

        AddUserCommandHandler handler = new AddUserCommandHandler(userRepository);
        assertNotNull(handler.handle(request));
    }

    @Test
    public void testHandle_WhenUsernameIsTaken_ShouldThrowException() {
        AddUserCommandRequest request = new AddUserCommandRequest();
        request.setUsername("tezi");
        request.setPassword("password");

        IUserRepository userRepository = new MockUserRepo(){
            @Override
            public void addUser(User user) {

            }

            @Override
            public boolean isUsernameTaken(String username) {
                return true;
            }
        };

        AddUserCommandHandler handler = new AddUserCommandHandler(userRepository);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> handler.handle(request));
        assertEquals("Username " + request.getUsername() + " is already taken!", exception.getMessage());
    }

    public void testHandle_WhenUsernameIsEmpty_ShouldThrowException() {
        AddUserCommandRequest request = new AddUserCommandRequest();
        request.setUsername("");
        request.setPassword("password");

        IUserRepository userRepository = new MockUserRepo(){
            @Override
            public void addUser(User user) {

            }

            @Override
            public boolean isUsernameTaken(String username) {
                return false;
            }
        };

        AddUserCommandHandler handler = new AddUserCommandHandler(userRepository);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> handler.handle(request));
        assertEquals("Username and Password mustn't be empty", exception.getMessage());
    }
}
