package main.application.queries.user.ReceivedMessagesQuery;

import main.domain.Message;

import java.util.List;

public class ReceivedMessagesQueryResponse {
    private List<Message> receivedMessages;

    public List<Message> getMessages() {
        return receivedMessages;
    }

    public void setMessages(List<Message> requests) {
        this.receivedMessages = requests;
    }
}
