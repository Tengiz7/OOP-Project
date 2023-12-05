package main.application.queries.user.SentMessagesQuery;

import main.domain.Message;

import java.util.List;

public class SentMessagesQueryResponse {
    private List<Message> sentMessages;

    public List<Message> getMessages() {
        return sentMessages;
    }

    public void setMessages(List<Message> requests) {
        this.sentMessages = requests;
    }
}
