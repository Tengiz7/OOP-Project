package Infrastructure;

import junit.framework.TestCase;
import main.domain.User;
import main.infrastructure.FriendRequestsRepository;
import main.infrastructure.UserRepository;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FriendRequestRepositoryTests extends TestCase {

    private final BasicDataSource dataSource;
    private final FriendRequestsRepository friendRequestsRepository;
    private final UserRepository userRepository;

    public FriendRequestRepositoryTests() {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/oop_db_test");
        dataSource.setUsername("test");
        dataSource.setPassword("password");
        try {
            friendRequestsRepository = new FriendRequestsRepository(dataSource.getConnection());
            userRepository = new UserRepository(dataSource.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void setUp() throws SQLException {
        userRepository.addUser(new User("sender", "pas"));
        userRepository.addUser(new User("receiver", "pas"));
        dataSource.getConnection().prepareStatement("DELETE FROM friendrequests").execute();
    }

    @AfterAll
    public void tearDown() throws SQLException {
        dataSource.getConnection().prepareStatement("DELETE FROM oop_db_test.friendrequests").execute();
        dataSource.getConnection().prepareStatement("DELETE FROM oop_db_test.users").execute();
    }

    @Test
    public void testAddFriendRequest() {
        friendRequestsRepository.addFriendRequest("sender", "receiver", "message");
        assertEquals(1, friendRequestsRepository.getSentFriendRequests("sender").size());
    }

    @Test
    public void testRemoveFriendRequest() {
        friendRequestsRepository.addFriendRequest("sender", "receiver", "message");
        friendRequestsRepository.removeFriendRequest("sender", "receiver");
        assertEquals(0, friendRequestsRepository.getSentFriendRequests("sender").size());
    }

    @Test
    public void testFriendRequestExists() {
        friendRequestsRepository.addFriendRequest("sender", "receiver", "message");
        assertTrue(friendRequestsRepository.friendRequestExists("sender", "receiver"));
    }

    @Test
    public void testGetSentFriendRequests() {
        friendRequestsRepository.addFriendRequest("sender", "receiver", "message");
        assertEquals(1, friendRequestsRepository.getSentFriendRequests("sender").size());
    }
}
