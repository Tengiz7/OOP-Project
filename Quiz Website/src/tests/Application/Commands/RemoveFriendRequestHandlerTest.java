package Application.Commands;

import MockRepositories.MockFriendRequestRepo;
import MockRepositories.MockUserRepo;
import junit.framework.TestCase;
import main.application.commands.user.RemoveFriendRequestCommand.RemoveFriendRequestCommandHandler;
import main.application.commands.user.RemoveFriendRequestCommand.RemoveFriendRequestCommandRequest;
import main.application.users.IFriendRequestRepository;
import main.application.users.IUserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;

public class RemoveFriendRequestHandlerTest extends TestCase {

    @Test
    public void testWhenEverythingIsFine_ShouldReturnResult() {
        RemoveFriendRequestCommandRequest request = new RemoveFriendRequestCommandRequest();
        request.setSenderUsername("tezi");
        request.setReceiverUsername("nika");
        IUserRepository userRepository = new MockUserRepo();
        IFriendRequestRepository friendRequestRepository = new MockFriendRequestRepo(){
            @Override
            public void removeFriendRequest(String senderName, String receiverName) {
                if(!(senderName.equals(request.getSenderUsername())
                        && receiverName.equals(request.getReceiverUsername()))){
                    throw new RuntimeException("Invalid Data");
                }
            }
        };

        RemoveFriendRequestCommandHandler handler = new RemoveFriendRequestCommandHandler(friendRequestRepository, userRepository);
        assertNotNull(handler.handle(request));
    }

    public void testHandle_WhenFriendRequestDoesNotExist_ShouldThrowException() {
        RemoveFriendRequestCommandRequest request = new RemoveFriendRequestCommandRequest();
        request.setSenderUsername("tezi");
        request.setReceiverUsername("nika");
        IUserRepository userRepository = new MockUserRepo();
        IFriendRequestRepository friendRequestRepository = new MockFriendRequestRepo(){
            @Override
            public boolean friendRequestExists(String senderName, String receiverName) {
                return false;
            }
        };

        RemoveFriendRequestCommandHandler handler = new RemoveFriendRequestCommandHandler(friendRequestRepository, userRepository);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> handler.handle(request));
        assertEquals("Friend request doesn't exist", exception.getMessage());
    }
    public void testHandle_WhenUserDoesNotExist_ShouldThrowException() {
        RemoveFriendRequestCommandRequest request = new RemoveFriendRequestCommandRequest();
        request.setSenderUsername("tezi");
        request.setReceiverUsername("nika");
        IUserRepository userRepository = new MockUserRepo(){
            @Override
            public boolean isUsernameTaken(String username) {
                return false;
            }
        };
        IFriendRequestRepository friendRequestRepository = new MockFriendRequestRepo();

        RemoveFriendRequestCommandHandler handler = new RemoveFriendRequestCommandHandler(friendRequestRepository, userRepository);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> handler.handle(request));
        assertEquals("username doesn't exist", exception.getMessage());
    }
}
