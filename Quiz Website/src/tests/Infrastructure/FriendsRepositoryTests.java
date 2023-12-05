package Infrastructure;

import junit.framework.TestCase;
import main.domain.User;
import main.infrastructure.FriendRequestsRepository;
import main.infrastructure.FriendsRepository;
import main.infrastructure.UserRepository;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class FriendsRepositoryTests extends TestCase {

    private final BasicDataSource dataSource;
    private final FriendsRepository friendsRepository;
    private final UserRepository userRepository;

    public FriendsRepositoryTests() {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/oop_db_test");
        dataSource.setUsername("test");
        dataSource.setPassword("password");
        try {
            friendsRepository = new FriendsRepository(dataSource.getConnection());
            userRepository = new UserRepository(dataSource.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void setUp() throws SQLException {
        userRepository.addUser(new User("sender", "pas"));
        userRepository.addUser(new User("receiver", "pas"));
        dataSource.getConnection().prepareStatement("DELETE FROM friendships").execute();
    }

    @AfterAll
    public void tearDown() throws SQLException {
        dataSource.getConnection().prepareStatement("DELETE FROM oop_db_test.friendships").execute();
        dataSource.getConnection().prepareStatement("DELETE FROM oop_db_test.users").execute();
    }

    public void testAddFriend() {
        friendsRepository.addFriend("sender", "receiver", LocalDateTime.now());
        assertEquals(1, friendsRepository.getFriendships("sender").size());
    }

    public void testRemoveFriendship() {
        friendsRepository.addFriend("sender", "receiver", LocalDateTime.now());
        friendsRepository.removeFriendship("sender", "receiver");
        assertEquals(0, friendsRepository.getFriendships("sender").size());
    }

    public void testFriendshipExists() {
        friendsRepository.addFriend("sender", "receiver", LocalDateTime.now());
        assertTrue(friendsRepository.friendshipExists("sender", "receiver"));
    }
}
