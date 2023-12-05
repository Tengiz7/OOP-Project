package Application.Commands;

import MockRepositories.MockFriendRequestRepo;
import MockRepositories.MockFriendsRepo;
import MockRepositories.MockUserRepo;
import junit.framework.TestCase;
import main.application.commands.user.AddFriendshipCommand.AddFriendshipCommandHandler;
import main.application.commands.user.AddFriendshipCommand.AddFriendshipCommandRequest;
import main.application.commands.user.AddFriendshipCommand.AddFriendshipCommandResponse;
import main.application.users.IFriendRequestRepository;
import main.application.users.IFriendsRepository;
import main.application.users.IUserRepository;
import main.domain.User;
import main.domain.enums.UserStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class AddFriendshipHandlerTest extends TestCase {

    @Test
    public void testWhenEverythingIsFine_ShouldReturnResult() {
        AddFriendshipCommandRequest request = new AddFriendshipCommandRequest();
        request.setUser("tezi");
        request.setFutureFriend("nika");
        request.setLocalDateTime(LocalDateTime.now());

        IFriendsRepository friendsRepository = new IFriendsRepository() {
            int counter = 0;

            @Override
            public List<String> getFriendships(String user) {
                return null;
            }

            @Override
            public void addFriend(String user, String futureFriend, LocalDateTime localDateTime) {
                if (counter == 0) {
                    if (!(user.equals(request.getUser()) && futureFriend.equals(request.getFutureFriend()) && localDateTime.equals(request.getLocalDateTime()))) {
                        throw new RuntimeException("Invalid data");
                    }
                    counter++;
                } else {
                    if (!(user.equals(request.getFutureFriend()) && futureFriend.equals(request.getUser()) && localDateTime.equals(request.getLocalDateTime()))) {
                        throw new RuntimeException("Invalid data");
                    }
                }

            }

            @Override
            public void removeFriendship(String user, String oldFriend) {

            }

            @Override
            public boolean friendshipExists(String user1, String user2) {
                return false;
            }
        };
        IFriendRequestRepository friendRequestRepository = new MockFriendRequestRepo();
        IUserRepository userRepository = new MockUserRepo();

        AddFriendshipCommandHandler handler = new AddFriendshipCommandHandler(friendRequestRepository,
                friendsRepository, userRepository);
        AddFriendshipCommandResponse addFriendshipCommandResponse = handler.handle(request);
        assertTrue(addFriendshipCommandResponse.isAdded());
    }

    public void testHandle_WhenFriendshipExists_ShouldThrowException() {
        // Arrange
        AddFriendshipCommandRequest request = new AddFriendshipCommandRequest();
        request.setUser("tezi");
        request.setFutureFriend("nika");
        request.setLocalDateTime(LocalDateTime.now());

        IFriendsRepository friendsRepository = new IFriendsRepository() {
            @Override
            public List<String> getFriendships(String user) {
                return null;
            }

            @Override
            public void addFriend(String user, String futureFriend, LocalDateTime localDateTime) {
            }

            @Override
            public void removeFriendship(String user, String oldFriend) {
            }

            @Override
            public boolean friendshipExists(String user1, String user2) {
                return true;  // Simulate existing friendship
            }
        };

        IFriendRequestRepository friendRequestRepository = new MockFriendRequestRepo();

        IUserRepository userRepository = new MockUserRepo();

        AddFriendshipCommandHandler handler = new AddFriendshipCommandHandler(friendRequestRepository,
                friendsRepository, userRepository);

        assertThrows("Friend already exists", RuntimeException.class, () -> handler.handle(request));
    }

    public void testHandle_WhenReceiverUsernameDoesNotExist_ShouldThrowException() {
        // Arrange
        AddFriendshipCommandRequest request = new AddFriendshipCommandRequest();
        request.setUser("tezi");
        request.setFutureFriend("nika");
        request.setLocalDateTime(LocalDateTime.now());

        IFriendsRepository friendsRepository = new MockFriendsRepo();

        IFriendRequestRepository friendRequestRepository = new MockFriendRequestRepo();

        IUserRepository userRepository = new IUserRepository() {
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
                return false;  // Simulate sender username exists
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

        AddFriendshipCommandHandler handler = new AddFriendshipCommandHandler(friendRequestRepository,
                friendsRepository, userRepository);

        // Act and Assert
        assertThrows("username doesn't exist",RuntimeException.class, () -> handler.handle(request));
    }

    public void testHandle_WhenFriendRequestDoesNotExist_ShouldThrowException() {
        // Arrange
        AddFriendshipCommandRequest request = new AddFriendshipCommandRequest();
        request.setUser("tezi");
        request.setFutureFriend("nika");
        request.setLocalDateTime(LocalDateTime.now());

        IFriendsRepository friendsRepository = new MockFriendsRepo();

        IFriendRequestRepository friendRequestRepository = new IFriendRequestRepository() {
            @Override
            public void addFriendRequest(String senderName, String receiverName, String message) {

            }

            @Override
            public void removeFriendRequest(String senderName, String receiverName) {

            }

            @Override
            public boolean friendRequestExists(String senderName, String receiverName) {
                return false;
            }

            @Override
            public List<String> getSentFriendRequests(String senderName) {
                return null;
            }

            @Override
            public List<String> getReceivedFriendRequests(String receiverName) {
                return null;
            }
        };

        IUserRepository userRepository = new MockUserRepo();

        AddFriendshipCommandHandler handler = new AddFriendshipCommandHandler(friendRequestRepository,
                friendsRepository, userRepository);

        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> handler.handle(request));
        assertEquals("Friend request doesn't exists", exception.getMessage());
    }



}
