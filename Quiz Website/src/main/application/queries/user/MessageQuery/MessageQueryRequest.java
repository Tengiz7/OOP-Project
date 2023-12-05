package main.application.queries.user.MessageQuery;

import main.mediator.abstractions.IRequest;

public class MessageQueryRequest implements IRequest<MessageQueryResponse> {
    private int messageId;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public MessageQueryRequest(int messageId) {
        this.messageId = messageId;
    }
}
