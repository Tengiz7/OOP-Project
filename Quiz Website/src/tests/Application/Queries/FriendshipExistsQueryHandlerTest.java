package Application.Queries;

import MockRepositories.MockFriendsRepo;
import junit.framework.TestCase;
import main.application.queries.user.FriendshipExistsQuery.FriendshipExistsQueryHandler;
import main.application.queries.user.FriendshipExistsQuery.FriendshipExistsQueryRequest;
import main.application.queries.user.FriendshipExistsQuery.FriendshipExistsQueryResponse;
import main.application.users.IFriendsRepository;
import org.junit.jupiter.api.Test;

public class FriendshipExistsQueryHandlerTest extends TestCase {

    @Test
    public void testWhenEverythingIsFine_ShouldReturnResult() {
        FriendshipExistsQueryRequest request = new FriendshipExistsQueryRequest();
        request.setUser_1("Tezi");
        request.setUser_2("Lizi");

        IFriendsRepository iFriendsRepository_1 = new MockFriendsRepo(){
            @Override
            public boolean friendshipExists(String user1, String user2) {
                return true;
            }
        };
        IFriendsRepository iFriendsRepository_2 = new MockFriendsRepo(){
            @Override
            public boolean friendshipExists(String user1, String user2) {
                return false;
            }
        };

        FriendshipExistsQueryHandler friendshipExistsQueryHandler_1 = new FriendshipExistsQueryHandler(iFriendsRepository_1);
        FriendshipExistsQueryResponse friendshipExistsQueryResponse_1 = friendshipExistsQueryHandler_1.handle(request);
        assertTrue(friendshipExistsQueryResponse_1.getExists());

        FriendshipExistsQueryHandler friendshipExistsQueryHandler_2 = new FriendshipExistsQueryHandler(iFriendsRepository_2);
        FriendshipExistsQueryResponse friendshipExistsQueryResponse_2 = friendshipExistsQueryHandler_2.handle(request);
        assertFalse(friendshipExistsQueryResponse_2.getExists());
    }
}
