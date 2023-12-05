package main.application.queries.user.GetReceivedFriendRequestsQuery;

import java.util.List;

public class ReceivedFriendRequestsQueryResponse {
    private List<String> requests;

    public List<String> getRequests() {
        return requests;
    }

    public void setRequests(List<String> requests) {
        this.requests = requests;
    }
}
