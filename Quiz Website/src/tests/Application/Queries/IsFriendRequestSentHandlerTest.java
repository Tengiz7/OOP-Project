package Application.Queries;

import MockRepositories.MockFriendRequestRepo;
import junit.framework.TestCase;
import main.application.queries.user.isFriendRequestSent.IsFriendRequestSentHandler;
import main.application.queries.user.isFriendRequestSent.IsFriendRequestSentRequest;
import main.application.queries.user.isFriendRequestSent.IsFriendRequestSentResponse;
import main.application.users.IFriendRequestRepository;
import org.junit.jupiter.api.Test;

public class IsFriendRequestSentHandlerTest extends TestCase {
    @Test
    public void testWhenEverythingIsFine_ShouldReturnResult() {
        IsFriendRequestSentRequest request = new IsFriendRequestSentRequest();
        request.setSender("Tezi");
        request.setReceiver("Lizi");

        IFriendRequestRepository iFriendRequestRepository_1 = new MockFriendRequestRepo(){
            @Override
            public boolean friendRequestExists(String senderName, String receiverName) {
                return true;
            }
        };
        IFriendRequestRepository iFriendRequestRepository_2 = new MockFriendRequestRepo(){
            @Override
            public boolean friendRequestExists(String senderName, String receiverName) {
                return false;
            }
        };
        IsFriendRequestSentHandler isFriendRequestSentHandler_1 = new IsFriendRequestSentHandler(iFriendRequestRepository_1);
        IsFriendRequestSentResponse isFriendRequestSentResponse_1 = isFriendRequestSentHandler_1.handle(request);
        assertTrue(isFriendRequestSentResponse_1.getExists());

        IsFriendRequestSentHandler isFriendRequestSentHandler_2 = new IsFriendRequestSentHandler(iFriendRequestRepository_2);
        IsFriendRequestSentResponse isFriendRequestSentResponse_2 = isFriendRequestSentHandler_2.handle(request);
        assertFalse(isFriendRequestSentResponse_2.getExists());
    }
}
