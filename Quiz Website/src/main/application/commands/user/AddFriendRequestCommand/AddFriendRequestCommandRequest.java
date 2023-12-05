package main.application.commands.user.AddFriendRequestCommand;

import main.mediator.abstractions.IRequest;

public class AddFriendRequestCommandRequest implements IRequest<AddFriendRequestCommandResponse> {
    private String senderUsername;
    private String receiverUsername;
    private String message;

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
}
