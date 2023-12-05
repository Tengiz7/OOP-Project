package Application.Commands;

import MockRepositories.MockFriendsRepo;
import MockRepositories.MockUserRepo;
import junit.framework.TestCase;
import main.application.commands.user.RemoveFriendshipCommand.RemoveFriendshipCommandHandler;
import main.application.commands.user.RemoveFriendshipCommand.RemoveFriendshipCommandRequest;
import main.application.users.IFriendsRepository;
import main.application.users.IUserRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class RemoveFriendshipCommandHandlerTest extends TestCase {

    @Test
    public void testWhenEverythingIsFine_ShouldReturnResult() {
        RemoveFriendshipCommandRequest request = new RemoveFriendshipCommandRequest();
        request.setUser("tezi");
        request.setOldFriend("nika");
        IFriendsRepository friendsRepository = new IFriendsRepository() {
            int counter = 0;

            @Override
            public List<String> getFriendships(String user) {
                return null;
            }

            @Override
            public void addFriend(String user, String futureFriend, LocalDateTime localDateTime) {

            }

            @Override
            public void removeFriendship(String user, String oldFriend) {
                if (counter == 0) {
                    if (!(user.equals(request.getUser()) && oldFriend.equals(request.getOldFriend()))) {
                        throw new RuntimeException("Invalid data");
                    }
                    counter++;
                } else {
                    if (!(user.equals(request.getOldFriend()) && oldFriend.equals(request.getUser()))) {
                        throw new RuntimeException("Invalid data");
                    }
                }
            }
            @Override
            public boolean friendshipExists(String user1, String user2) {
                return true;
            }
        };

        IUserRepository userRepository = new MockUserRepo();

        RemoveFriendshipCommandHandler handler = new RemoveFriendshipCommandHandler(friendsRepository, userRepository);
        assertNotNull(handler.handle(request));
    }

    public void testHandle_WhenReceiverUsernameDoesNotExist_ShouldThrowException() {
        // Arrange
        RemoveFriendshipCommandRequest request = new RemoveFriendshipCommandRequest();
        request.setUser("tezi");
        request.setOldFriend("nika");

        IFriendsRepository friendsRepository = new MockFriendsRepo();

        IUserRepository userRepository = new MockUserRepo(){
            @Override
            public boolean isUsernameTaken(String username) {
                return false;
            }
        };

        RemoveFriendshipCommandHandler handler = new RemoveFriendshipCommandHandler(friendsRepository, userRepository);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> handler.handle(request));
        assertEquals("Username doesn't exist", exception.getMessage());
    }
}
