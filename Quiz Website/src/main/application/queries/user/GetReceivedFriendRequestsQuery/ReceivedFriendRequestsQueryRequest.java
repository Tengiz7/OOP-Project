package main.application.queries.user.GetReceivedFriendRequestsQuery;

import main.mediator.abstractions.IRequest;

public class ReceivedFriendRequestsQueryRequest implements IRequest<ReceivedFriendRequestsQueryResponse> {
    private String receiverUsername;

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }
}
