package main.application.commands.user.AddMessageCommand;

import main.mediator.abstractions.IRequest;

import java.time.LocalDateTime;

public class AddMessageCommandRequest implements IRequest<AddMessageCommandResponse> {
    private String senderUsername;
    private String receiverUsername;
    private String message;
    private LocalDateTime localDateTime;

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
