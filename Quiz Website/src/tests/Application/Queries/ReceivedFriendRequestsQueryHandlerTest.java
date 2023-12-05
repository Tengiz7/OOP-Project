package Application.Queries;

import MockRepositories.MockFriendRequestRepo;
import MockRepositories.MockUserRepo;
import junit.framework.TestCase;
import main.application.queries.user.GetReceivedFriendRequestsQuery.ReceivedFriendRequestsQueryHandler;
import main.application.queries.user.GetReceivedFriendRequestsQuery.ReceivedFriendRequestsQueryRequest;
import main.application.queries.user.GetReceivedFriendRequestsQuery.ReceivedFriendRequestsQueryResponse;
import main.application.users.IFriendRequestRepository;
import main.application.users.IUserRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class ReceivedFriendRequestsQueryHandlerTest extends TestCase {
    @Test
    public void testWhenEverythingIsFine_ShouldReturnResult() {
        ReceivedFriendRequestsQueryRequest request = new ReceivedFriendRequestsQueryRequest();
        request.setReceiverUsername("Tezi");
        IUserRepository userRepository = new MockUserRepo();
        List<String> requests = Arrays.asList("Xajo", "lizi");
        IFriendRequestRepository friendRequestRepository = new MockFriendRequestRepo(){
            @Override
            public List<String> getReceivedFriendRequests(String receiverName) {
                return requests;
            }
        };

        ReceivedFriendRequestsQueryHandler receivedFriendRequestsQueryHandler = new ReceivedFriendRequestsQueryHandler(friendRequestRepository,userRepository);
        ReceivedFriendRequestsQueryResponse receivedFriendRequestsQueryResponse = receivedFriendRequestsQueryHandler.handle(request);
        assertNotNull(receivedFriendRequestsQueryResponse);
        assertTrue(receivedFriendRequestsQueryResponse.getRequests().containsAll(requests));
    }

    public void testHandle_WhenUsernameDoesNotExist_ShouldThrowException() {
        ReceivedFriendRequestsQueryRequest request = new ReceivedFriendRequestsQueryRequest();
        request.setReceiverUsername("Tezi");
        IUserRepository userRepository = new MockUserRepo(){
            @Override
            public boolean isUsernameTaken(String username) {
                return false;
            }
        };
        IFriendRequestRepository friendRequestRepository = new MockFriendRequestRepo();


        ReceivedFriendRequestsQueryHandler receivedFriendRequestsQueryHandler = new ReceivedFriendRequestsQueryHandler(friendRequestRepository,userRepository);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> receivedFriendRequestsQueryHandler.handle(request));
        assertEquals("Receiver username doesn't exist", exception.getMessage());
    }
}
