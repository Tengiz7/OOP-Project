package Application.Queries;

import MockRepositories.MockFriendRequestRepo;
import MockRepositories.MockUserRepo;
import junit.framework.TestCase;
import main.application.queries.user.GetSentFriendRequestsQuery.SentFriendRequestsQueryHandler;
import main.application.queries.user.GetSentFriendRequestsQuery.SentFriendRequestsQueryRequest;
import main.application.queries.user.GetSentFriendRequestsQuery.SentFriendRequestsQueryResponse;
import main.application.users.IFriendRequestRepository;
import main.application.users.IUserRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class SentFriendRequestsQueryHandlerTest extends TestCase {
    @Test
    public void testWhenEverythingIsFine_ShouldReturnResult() {
        SentFriendRequestsQueryRequest request = new SentFriendRequestsQueryRequest();
        request.setSenderUsername("Tezi");
        IUserRepository userRepository = new MockUserRepo();
        List<String> requests = Arrays.asList("Xajo", "lizi");
        IFriendRequestRepository friendRequestRepository = new MockFriendRequestRepo(){
            @Override
            public List<String> getSentFriendRequests(String senderName) {
                return requests;
            }
        };

        SentFriendRequestsQueryHandler sentFriendRequestsQueryHandler = new SentFriendRequestsQueryHandler(friendRequestRepository,userRepository);
        SentFriendRequestsQueryResponse sentFriendRequestsQueryResponse = sentFriendRequestsQueryHandler.handle(request);
        assertNotNull(sentFriendRequestsQueryResponse);
        assertTrue(sentFriendRequestsQueryResponse.getRequests().containsAll(requests));
    }

    public void testHandle_WhenUsernameDoesNotExist_ShouldThrowException() {
        SentFriendRequestsQueryRequest request = new SentFriendRequestsQueryRequest();
        request.setSenderUsername("Tezi");
        IUserRepository userRepository = new MockUserRepo(){
            @Override
            public boolean isUsernameTaken(String username) {
                return false;
            }
        };
        IFriendRequestRepository friendRequestRepository = new MockFriendRequestRepo();


        SentFriendRequestsQueryHandler sentFriendRequestsQueryHandler = new SentFriendRequestsQueryHandler(friendRequestRepository,userRepository);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> sentFriendRequestsQueryHandler.handle(request));
        assertEquals("Sender username doesn't exist", exception.getMessage());
    }
}
