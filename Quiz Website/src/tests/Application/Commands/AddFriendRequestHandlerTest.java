package Application.Commands;

import MockRepositories.MockFriendRequestRepo;
import MockRepositories.MockUserRepo;
import junit.framework.TestCase;
import main.application.commands.user.AddFriendRequestCommand.AddFriendRequestCommandHandler;
import main.application.commands.user.AddFriendRequestCommand.AddFriendRequestCommandRequest;
import main.application.users.IFriendRequestRepository;
import main.application.users.IUserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;

public class AddFriendRequestHandlerTest extends TestCase {
    @Test
    public void testWhenEverythingIsFine_ShouldReturnResult() {
        AddFriendRequestCommandRequest request = new AddFriendRequestCommandRequest();
        request.setSenderUsername("tezi");
        request.setReceiverUsername("nika");
        request.setMessage("me is var partyze ro gamicani");
        IUserRepository userRepository = new MockUserRepo();
        IFriendRequestRepository friendRequestRepository = new MockFriendRequestRepo(){
            @Override
            public void addFriendRequest(String senderName, String receiverName, String message) {
                if(!(senderName.equals(request.getSenderUsername())
                        && receiverName.equals(request.getReceiverUsername())
                        && message.equals(request.getMessage()))){
                    throw new RuntimeException("Invalid Data");
                }
            }

            @Override
            public boolean friendRequestExists(String senderName, String receiverName) {
                return false;
            }
        };

        AddFriendRequestCommandHandler handler = new AddFriendRequestCommandHandler(friendRequestRepository, userRepository);
        assertNotNull(handler.handle(request));
    }

    @Test
    public void testHandle_WhenFriendRequestExists_ShouldThrowException() {
        AddFriendRequestCommandRequest request = new AddFriendRequestCommandRequest();
        request.setSenderUsername("tezi");
        request.setReceiverUsername("nika");
        request.setMessage("me is var partyze ro gamicani");
        IUserRepository userRepository = new MockUserRepo(){};
        IFriendRequestRepository friendRequestRepository = new MockFriendRequestRepo();

        AddFriendRequestCommandHandler handler = new AddFriendRequestCommandHandler(friendRequestRepository, userRepository);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> handler.handle(request));
        assertEquals("Friend request already exists", exception.getMessage());
    }

    @Test
    public void testHandle_WhenUsernameDoesNotExist_ShouldThrowException() {
        AddFriendRequestCommandRequest request = new AddFriendRequestCommandRequest();
        request.setSenderUsername("tezi");
        request.setReceiverUsername("nika");
        request.setMessage("me is var partyze ro gamicani");
        IUserRepository userRepository = new MockUserRepo(){
            @Override
            public boolean isUsernameTaken(String username) {
                return false;
            }
        };
        IFriendRequestRepository friendRequestRepository = new MockFriendRequestRepo(){
            @Override
            public boolean friendRequestExists(String senderName, String receiverName) {
                return false;
            }
        };

        AddFriendRequestCommandHandler handler = new AddFriendRequestCommandHandler(friendRequestRepository, userRepository);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> handler.handle(request));
        assertEquals("Username doesn't exist", exception.getMessage());
    }
    @Test
    public void testHandle_SenderUsernameEqualsReceiverUsername_ShouldThrowException() {
        AddFriendRequestCommandRequest request = new AddFriendRequestCommandRequest();
        request.setSenderUsername("tezi");
        request.setReceiverUsername("tezi");
        request.setMessage("me is var partyze ro gamicani");
        IUserRepository userRepository = new MockUserRepo();
        IFriendRequestRepository friendRequestRepository = new MockFriendRequestRepo();
        AddFriendRequestCommandHandler handler = new AddFriendRequestCommandHandler(friendRequestRepository, userRepository);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> handler.handle(request));
        assertEquals("You can't send friend request to yourself", exception.getMessage());
    }
}
