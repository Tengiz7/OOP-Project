package main.application.queries.user.SentMessagesQuery;

import main.mediator.abstractions.IRequest;

public class SentMessagesQueryRequest implements IRequest<SentMessagesQueryResponse> {
    private String senderUsername;

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }
}
