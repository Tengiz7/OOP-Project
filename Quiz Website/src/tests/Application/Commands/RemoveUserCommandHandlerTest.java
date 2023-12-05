package Application.Commands;

import MockRepositories.MockUserRepo;
import junit.framework.TestCase;
import main.application.commands.user.RemoveUserCommand.RemoveUserCommandHandler;
import main.application.commands.user.RemoveUserCommand.RemoveUserCommandRequest;
import main.application.users.IUserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;

public class RemoveUserCommandHandlerTest extends TestCase {
    @Test
    public void testWhenEverythingIsFine_ShouldReturnResult() {
        RemoveUserCommandRequest request = new RemoveUserCommandRequest("tezi");
        IUserRepository userRepository = new MockUserRepo(){
            @Override
            public void removeUser(String username) {
                if(!username.equals(request.getUsername())){
                    throw new RuntimeException("Invalid Data");
                }
            }

            @Override
            public boolean isUsernameTaken(String username) {
                return true;
            }
        };

        RemoveUserCommandHandler handler = new RemoveUserCommandHandler(userRepository);
        assertNotNull(handler.handle(request));
    }

    @Test
    public void testHandle_WhenUsernameIsTaken_ShouldThrowException() {
        RemoveUserCommandRequest request = new RemoveUserCommandRequest("tezi");
        IUserRepository userRepository = new MockUserRepo(){

            @Override
            public boolean isUsernameTaken(String username) {
                return false;
            }
        };

        RemoveUserCommandHandler handler = new RemoveUserCommandHandler(userRepository);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> handler.handle(request));
        assertEquals("No user found with this ID", exception.getMessage());
    }
}
