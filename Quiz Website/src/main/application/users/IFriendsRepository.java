package main.application.users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface IFriendsRepository {
    public List<String> getFriendships(String user);

    public void addFriend (String user, String futureFriend, LocalDateTime localDateTime);

    public void removeFriendship (String user, String oldFriend);

    public boolean friendshipExists (String user1, String user2);
}
