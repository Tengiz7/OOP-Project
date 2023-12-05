package main.application.queries.user.ReceivedMessagesQuery;

import main.mediator.abstractions.IRequest;

public class ReceivedMessagesQueryRequest implements IRequest<ReceivedMessagesQueryResponse> {
    private String receiverUsername;

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }
}
