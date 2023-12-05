package main.application.users;

import main.domain.Message;

import java.time.LocalDateTime;
import java.util.List;

public interface IMessagesRepository {
    public void sendMessage (String senderName, String receiverName, String message, LocalDateTime localDateTime);
    public List<Message> getReceivedMessages (String receiverName);
    public List<Message> getSentMessages (String senderName);
    void deleteMessage (int messageID);
    boolean messageExists (int messageID);
    public Message getMessage (int messageID);
}
