package main.application.users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IFriendRequestRepository {
    public void addFriendRequest (String senderName, String receiverName, String message);
    public void removeFriendRequest (String senderName, String receiverName);

    public boolean friendRequestExists (String senderName, String receiverName);

    public List<String> getSentFriendRequests(String senderName);

    public List<String> getReceivedFriendRequests(String receiverName);
}
