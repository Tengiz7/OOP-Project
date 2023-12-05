package main.application.queries.user.isFriendRequestSent;

import main.mediator.abstractions.IRequest;

public class IsFriendRequestSentRequest implements IRequest<IsFriendRequestSentResponse> {
    private String sender;
    private String receiver;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
