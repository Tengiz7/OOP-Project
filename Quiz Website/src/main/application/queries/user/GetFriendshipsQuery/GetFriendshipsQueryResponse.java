package main.application.queries.user.GetFriendshipsQuery;

import java.util.List;

public class GetFriendshipsQueryResponse {
    private List<String> friends;

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}
