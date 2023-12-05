package Application.Queries;

import MockRepositories.MockFriendsRepo;
import MockRepositories.MockUserRepo;
import junit.framework.TestCase;
import main.application.queries.user.GetFriendshipsQuery.GetFriendshipsQueryHandler;
import main.application.queries.user.GetFriendshipsQuery.GetFriendshipsQueryRequest;
import main.application.queries.user.GetFriendshipsQuery.GetFriendshipsQueryResponse;
import main.application.users.IFriendsRepository;
import main.application.users.IUserRepository;
import main.domain.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class GetFriendshipsQueryHandlerTest extends TestCase {
    @Test
    public void testWhenEverythingIsFine_ShouldReturnCorrectResult() {
        GetFriendshipsQueryRequest request = new GetFriendshipsQueryRequest();
        request.setUser("Tezi");
        IUserRepository userRepository = new MockUserRepo();
        List<String> friends = Arrays.asList("Xajo", "lizi");
        IFriendsRepository friendsRepository = new MockFriendsRepo(){
            @Override
            public List<String> getFriendships(String user) {
                return friends;
            }
        };

        GetFriendshipsQueryHandler getFriendshipsQueryHandler = new GetFriendshipsQueryHandler(friendsRepository,userRepository);
        GetFriendshipsQueryResponse getFriendshipsQueryResponse = getFriendshipsQueryHandler.handle(request);
        assertNotNull(getFriendshipsQueryResponse);
        assertTrue(getFriendshipsQueryResponse.getFriends().containsAll(friends));
    }

    @Test
    public void testHandle_WhenUsernameDoesNotExist_ShouldThrowException() {
        GetFriendshipsQueryRequest request = new GetFriendshipsQueryRequest();
        request.setUser("Tezi");
        IUserRepository userRepository = new MockUserRepo(){
            @Override
            public boolean isUsernameTaken(String username) {
                return false;
            }
        };
        IFriendsRepository friendsRepository = new MockFriendsRepo();

        GetFriendshipsQueryHandler getFriendshipsQueryHandler = new GetFriendshipsQueryHandler(friendsRepository,userRepository);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> getFriendshipsQueryHandler.handle(request));
        assertEquals("username doesn't exist", exception.getMessage());
    }
}
