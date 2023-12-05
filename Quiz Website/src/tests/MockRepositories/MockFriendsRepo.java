package MockRepositories;

import main.application.users.IFriendsRepository;

import java.time.LocalDateTime;
import java.util.List;

public class MockFriendsRepo implements IFriendsRepository {
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
        return false;
    }
}
