package main.application.queries.user.GetSentFriendRequestsQuery;

import java.util.List;

public class SentFriendRequestsQueryResponse {
    private List<String> requests;

    public List<String> getRequests() {
        return requests;
    }

    public void setRequests(List<String> requests) {
        this.requests = requests;
    }
}
