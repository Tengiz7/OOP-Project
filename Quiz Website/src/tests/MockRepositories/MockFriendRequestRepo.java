package MockRepositories;

import main.application.users.IFriendRequestRepository;

import java.util.List;

public class MockFriendRequestRepo implements IFriendRequestRepository {
    @Override
    public void addFriendRequest(String senderName, String receiverName, String message) {
    }

    @Override
    public void removeFriendRequest(String senderName, String receiverName) {
    }

    @Override
    public boolean friendRequestExists(String senderName, String receiverName) {
        return true;
    }

    @Override
    public List<String> getSentFriendRequests(String senderName) {
        return null;
    }

    @Override
    public List<String> getReceivedFriendRequests(String receiverName) {
        return null;
    }
}
