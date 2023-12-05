package main.application.queries.user.GetSentFriendRequestsQuery;

import main.mediator.abstractions.IRequest;

public class SentFriendRequestsQueryRequest implements IRequest<SentFriendRequestsQueryResponse> {
    private String senderUsername;

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }
}
