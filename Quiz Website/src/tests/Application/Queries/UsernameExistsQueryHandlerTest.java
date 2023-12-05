package Application.Queries;

import MockRepositories.MockUserRepo;
import junit.framework.TestCase;
import main.application.queries.user.UsernameExistsQuery.UsernameExistsQueryHandler;
import main.application.queries.user.UsernameExistsQuery.UsernameExistsQueryRequest;
import main.application.queries.user.UsernameExistsQuery.UsernameExistsQueryResponse;
import main.application.users.IUserRepository;
import org.junit.jupiter.api.Test;

public class UsernameExistsQueryHandlerTest extends TestCase {

    @Test
    public void testWhenEverythingIsFine_ShouldReturnResult() {
        UsernameExistsQueryRequest request = new UsernameExistsQueryRequest();
        request.setUsername("Tezi");

        IUserRepository userRepository_1 = new MockUserRepo(){
            @Override
            public boolean isUsernameTaken(String username) {
                return true;
            }
        };
        IUserRepository userRepository_2 = new MockUserRepo(){
            @Override
            public boolean isUsernameTaken(String username) {
                return false;
            }
        };
        UsernameExistsQueryHandler usernameExistsQueryHandler_1 = new UsernameExistsQueryHandler(userRepository_1);
        UsernameExistsQueryResponse usernameExistsQueryResponse_1 = usernameExistsQueryHandler_1.handle(request);
        assertTrue(usernameExistsQueryResponse_1.getExists());

        UsernameExistsQueryHandler usernameExistsQueryHandler_2 = new UsernameExistsQueryHandler(userRepository_2);
        UsernameExistsQueryResponse usernameExistsQueryResponse_2 = usernameExistsQueryHandler_2.handle(request);
        assertFalse(usernameExistsQueryResponse_2.getExists());
    }
}
